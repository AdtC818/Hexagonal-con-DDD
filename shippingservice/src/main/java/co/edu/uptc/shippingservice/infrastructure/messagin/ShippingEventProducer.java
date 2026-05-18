package co.edu.uptc.shippingservice.infrastructure.messagin;

import co.edu.uptc.shippingservice.domain.model.OrderEvent;
import co.edu.uptc.shippingservice.infrastructure.utils.JsonUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;

@Service
public class ShippingEventProducer {

    private static final String TOPIC = "order_shipped_topic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void publishOrderShipped(OrderEvent event, String trackingCode) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("orderId", event.getOrderId());
        payload.put("customerDocument", event.getCustomerDocument());
        payload.put("productName", event.getProductName());
        payload.put("quantity", event.getQuantity());
        payload.put("trackingCode", trackingCode);
        payload.put("status", "SHIPPED");

        String json = JsonUtils.toJson(payload);
        kafkaTemplate.send(TOPIC, "ORDER_SHIPPED", json);
        System.out.println("[SHIPPING PRODUCER] ORDER_SHIPPED publicado -> " + json);
        System.out.println("[SHIPPING] Flujo completado exitosamente. Orden "
                + event.getOrderId() + " despachada. Tracking: " + trackingCode);
    }
}