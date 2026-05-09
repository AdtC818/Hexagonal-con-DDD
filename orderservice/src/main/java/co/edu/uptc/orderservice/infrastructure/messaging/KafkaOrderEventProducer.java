package co.edu.uptc.orderservice.infrastructure.messaging;

import co.edu.uptc.orderservice.domain.event.OrderCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KafkaOrderEventProducer {

    private static final String ORDER_CREATED_TOPIC = "order_created_topic";
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public KafkaOrderEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = new ObjectMapper();
    }

    public void publishOrderCreated(OrderCreatedEvent event) {
        try {
            // Re-creating the payload similar to OrderEvent JSON to not break consumers
            String json = objectMapper.writeValueAsString(new OrderEventPayload(
                    event.getOrderId(),
                    event.getCustomerId(),
                    event.getProductId(),
                    event.getQuantity(),
                    event.getTotalAmount(),
                    "PENDING"
            ));
            kafkaTemplate.send(ORDER_CREATED_TOPIC, "ORDER_CREATED", json);
            System.out.println("[KAFKA PRODUCER] ORDER_CREATED publicado -> " + json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    // Helper DTO for backward compatibility with the existing OrderEvent expected by other services
    private record OrderEventPayload(
            String orderId,
            String customerDocument,
            String productName,
            int quantity,
            double totalPrice,
            String status
    ) {}
}
