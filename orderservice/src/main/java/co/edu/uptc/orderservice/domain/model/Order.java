package co.edu.uptc.orderservice.domain.model;

import co.edu.uptc.orderservice.domain.event.OrderCreatedEvent;
import co.edu.uptc.orderservice.domain.valueobject.OrderId;
import co.edu.uptc.orderservice.domain.valueobject.OrderStatus;
import co.edu.uptc.orderservice.shared.domain.AggregateRoot;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order extends AggregateRoot<OrderId> {

    private List<String> items;
    private String customerId;
    private String paymentId;
    private String shipmentId;
    private String inventoryReservationId;
    private int quantity;
    private double totalAmount;
    private OrderStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    protected Order() {
        super();
    }

    private Order(OrderId id, List<String> items, String customerId, String paymentId, String shipmentId,
                  String inventoryReservationId, int quantity, double totalAmount, OrderStatus status,
                  LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id);
        this.items = items == null ? new ArrayList<>() : new ArrayList<>(items);
        this.customerId = customerId;
        this.paymentId = paymentId;
        this.shipmentId = shipmentId;
        this.inventoryReservationId = inventoryReservationId;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Order create(String customerId, String productId, int quantity, double totalAmount) {
        return create(customerId, List.of(productId), null, null, null, quantity, totalAmount);
    }

    public static Order create(String customerId, List<String> items, String paymentId, String shipmentId,
                               String inventoryReservationId, int quantity, double totalAmount) {
        OrderId newId = OrderId.generate();
        LocalDateTime now = LocalDateTime.now();
        Order order = new Order(newId, items, customerId, paymentId, shipmentId, inventoryReservationId,
                quantity, totalAmount, OrderStatus.PENDING, now, now);
        order.registerEvent(new OrderCreatedEvent(newId, customerId, order.getProductId(), quantity, totalAmount));
        return order;
    }
    
    public static Order reconstitute(OrderId id, String customerId, String productId, int quantity, double totalAmount, OrderStatus status) {
        return reconstitute(id, List.of(productId), customerId, null, null, null, quantity, totalAmount, status, null, null);
    }

    public static Order reconstitute(OrderId id, List<String> items, String customerId, String paymentId,
                                     String shipmentId, String inventoryReservationId, int quantity,
                                     double totalAmount, OrderStatus status, LocalDateTime createdAt,
                                     LocalDateTime updatedAt) {
        return new Order(id, items, customerId, paymentId, shipmentId, inventoryReservationId, quantity,
                totalAmount, status, createdAt, updatedAt);
    }

    public void update(List<String> items, String customerId, String paymentId, String shipmentId,
                       String inventoryReservationId, int quantity, double totalAmount, OrderStatus status) {
        this.items = items == null ? new ArrayList<>() : new ArrayList<>(items);
        this.customerId = customerId;
        this.paymentId = paymentId;
        this.shipmentId = shipmentId;
        this.inventoryReservationId = inventoryReservationId;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.status = status;
        this.updatedAt = LocalDateTime.now();
    }

    public void confirm() {
        this.status = OrderStatus.CONFIRMED;
        this.updatedAt = LocalDateTime.now();
    }
    
    public void processPayment() {
        this.status = OrderStatus.PAYMENT_PROCESSED;
        this.updatedAt = LocalDateTime.now();
    }
    
    public void reserveInventory() {
        this.status = OrderStatus.INVENTORY_RESERVED;
        this.updatedAt = LocalDateTime.now();
    }

    public void cancel() {
        this.status = OrderStatus.CANCELLED;
        this.updatedAt = LocalDateTime.now();
    }

    public void ship() {
        this.status = OrderStatus.SHIPPED;
        this.updatedAt = LocalDateTime.now();
    }

    public List<String> getItems() {
        return new ArrayList<>(items);
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public String getInventoryReservationId() {
        return inventoryReservationId;
    }

    public String getProductId() {
        return items == null || items.isEmpty() ? null : items.get(0);
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
