package uptc.edu.co.paymentservice.infrastructure.messaging;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import uptc.edu.co.paymentservice.application.services.PaymentService;
import uptc.edu.co.paymentservice.domain.model.OrderDTO;
import uptc.edu.co.paymentservice.utils.JsonUtils;

@Component
public class PaymentConsumer {

    private final PaymentService paymentService;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final JsonUtils jsonUtils;

    public PaymentConsumer(PaymentService paymentService, KafkaTemplate<String, String> kafkaTemplate, JsonUtils jsonUtils) {
        this.paymentService = paymentService;
        this.kafkaTemplate = kafkaTemplate;
        this.jsonUtils = jsonUtils;
    }

    @KafkaListener(topics = "order_created_topic", groupId = "payment_group_new_v1")
    public void handleOrderCreated(ConsumerRecord<String, String> record) {
        String message = record.value();
        OrderDTO order = jsonUtils.fromJson(message, OrderDTO.class);
        
        if (order.getTotalPrice() < 500) {
            paymentService.createPayment(order, "SUCCESS");
            
            String outMessage = jsonUtils.toJson(order);
            kafkaTemplate.send("payment_processed_topic", outMessage);
            
            System.out.println("[SAGA] Pago aprobado para producto: " + order.getProductName());
        } else {
            paymentService.createPayment(order, "REJECTED");
            
            String outMessage = jsonUtils.toJson(order);
            kafkaTemplate.send("payment_failed_topic", outMessage);
            
            System.out.println("[SAGA] Pago rechazado para: " + order.getProductName());
        }
    }

    @KafkaListener(topics = "inventory_failed_topic", groupId = "payment_group")
    public void handleInventoryFailure(ConsumerRecord<String, String> record) {
        String message = record.value();
        OrderDTO order = jsonUtils.fromJson(message, OrderDTO.class);
        
        paymentService.updatePaymentStatus(order.getOrderId(), "REFUNDED");
        
        String outMessage = jsonUtils.toJson(order);
        kafkaTemplate.send("order_cancel_topic", outMessage);
        
        System.out.println("[SAGA] Compensación: Reembolso por falta de stock de " + order.getProductName());
    }   
}
