package co.edu.uptc.orderservice.infrastructure.messaging;

import co.edu.uptc.orderservice.application.usecase.CancelOrderUseCase;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KafkaOrderEventConsumer {

    private final CancelOrderUseCase cancelOrderUseCase;
    private final ObjectMapper objectMapper;

    public KafkaOrderEventConsumer(CancelOrderUseCase cancelOrderUseCase) {
        this.cancelOrderUseCase = cancelOrderUseCase;
        this.objectMapper = new ObjectMapper();
    }

    @KafkaListener(topics = "payment_failed_topic", groupId = "order_compensation_group")
    public void handlePaymentFailed(ConsumerRecord<String, String> record) {
        System.out.println("[KAFKA CONSUMER] PAYMENT_FAILED recibido: " + record.value());
        cancelOrder(record.value());
    }

    @KafkaListener(topics = "inventory_failed_topic", groupId = "order_compensation_group")
    public void handleInventoryFailed(ConsumerRecord<String, String> record) {
        System.out.println("[KAFKA CONSUMER] INVENTORY_FAILED recibido: " + record.value());
        cancelOrder(record.value());
    }

    private void cancelOrder(String payload) {
        try {
            JsonNode rootNode = objectMapper.readTree(payload);
            String orderId = rootNode.get("orderId").asText();
            cancelOrderUseCase.execute(orderId);
        } catch (Exception e) {
            System.err.println("[KAFKA CONSUMER] Error en compensaciÃ³n: " + e.getMessage());
        }
    }
}
