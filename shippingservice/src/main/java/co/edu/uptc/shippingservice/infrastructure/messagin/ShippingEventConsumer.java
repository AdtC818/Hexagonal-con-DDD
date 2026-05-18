package co.edu.uptc.shippingservice.infrastructure.messagin;

import co.edu.uptc.shippingservice.application.ports.in.CreateShippingUseCase;
import co.edu.uptc.shippingservice.domain.model.OrderEvent;
import co.edu.uptc.shippingservice.domain.model.Shipping;
import co.edu.uptc.shippingservice.infrastructure.utils.JsonUtils;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ShippingEventConsumer {

    @Autowired
    private CreateShippingUseCase shipmentService;

    @Autowired
    private ShippingEventProducer shippingEventProducer;

    @KafkaListener(topics = "inventory_reserved_topic", groupId = "shipping_group")
    public void handleInventoryReserved(ConsumerRecord<String, String> record) {
        System.out.println("[SHIPPING CONSUMER] INVENTORY_RESERVED recibido. Payload=" + record.value());

        try {
            OrderEvent event = JsonUtils.fromJson(record.value(), OrderEvent.class);

            Shipping shipment = shipmentService.createShipping(
                    event.getOrderId(),
                    event.getCustomerDocument(),
                    event.getProductName(),
                    event.getQuantity()
            );

            shippingEventProducer.publishOrderShipped(event, shipment.getTrackingCode());

        } catch (Exception e) {
            System.err.println("[SHIPPING CONSUMER] Error procesando envío: " + e.getMessage());
        }
    }
}