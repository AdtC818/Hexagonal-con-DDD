package uptc.edu.co.inventoryservice.infrastructure.messaging;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import uptc.edu.co.inventoryservice.application.services.InventoryService;
import uptc.edu.co.inventoryservice.domain.model.OrderDTO;
import uptc.edu.co.inventoryservice.utils.JsonUtils;

@Service
public class InventoryConsumer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final InventoryService inventoryService;
    private final JsonUtils jsonUtils = new JsonUtils();

    public InventoryConsumer(KafkaTemplate<String, String> kafkaTemplate,
                             InventoryService inventoryService) {
        this.kafkaTemplate = kafkaTemplate;
        this.inventoryService = inventoryService;
    }

    @KafkaListener(topics = "payment_processed_topic", groupId = "inventory_group_new_v1")
    public void handlePaymentProcessed(ConsumerRecord<String, String> record) {
        String message = record.value();
        OrderDTO order = jsonUtils.fromJson(message, OrderDTO.class);

        boolean hasStock = inventoryService.checkAndReduceStock(order.getProductName(), order.getQuantity());

        if (hasStock) {
            System.out.println("[SAGA] Inventario confirmado para: " + order.getProductName());
            kafkaTemplate.send("inventory_reserved_topic", "INVENTORY_RESERVED", jsonUtils.toJson(order));
        } else {
            System.out.println("[SAGA] ERROR: Sin stock para: " + order.getProductName());
            kafkaTemplate.send("inventory_failed_topic", "INVENTORY_FAILED", jsonUtils.toJson(order));
        }
    }
}