package co.edu.uptc.orderservice.domain.model;

import co.edu.uptc.orderservice.domain.event.OrderCreatedEvent;
import co.edu.uptc.orderservice.domain.valueobject.OrderId;
import co.edu.uptc.orderservice.domain.valueobject.OrderStatus;
import co.edu.uptc.orderservice.shared.domain.AggregateRoot;

public class Order extends AggregateRoot<OrderId> {

    private String customerId; // corresponds to customerDocument
    private String productId;  // corresponds to productName
    private int quantity;
    private double totalAmount; // corresponds to totalPrice
    private OrderStatus status;

    protected Order() {
        super();
    }

    private Order(OrderId id, String customerId, String productId, int quantity, double totalAmount, OrderStatus status) {
        super(id);
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public static Order create(String customerId, String productId, int quantity, double totalAmount) {
        OrderId newId = OrderId.generate();
        Order order = new Order(newId, customerId, productId, quantity, totalAmount, OrderStatus.PENDING);
        order.registerEvent(new OrderCreatedEvent(newId, customerId, productId, quantity, totalAmount));
        return order;
    }
    
    public static Order reconstitute(OrderId id, String customerId, String productId, int quantity, double totalAmount, OrderStatus status) {
        return new Order(id, customerId, productId, quantity, totalAmount, status);
    }

    public void confirm() {
        this.status = OrderStatus.CONFIRMED;
        // Optionally register event OrderConfirmedEvent
    }
    
    public void processPayment() {
        this.status = OrderStatus.PAYMENT_PROCESSED;
    }
    
    public void reserveInventory() {
        this.status = OrderStatus.INVENTORY_RESERVED;
    }

    public void cancel() {
        this.status = OrderStatus.CANCELLED;
        // Optionally register event OrderCancelledEvent
    }

    public void ship() {
        this.status = OrderStatus.SHIPPED;
        // Optionally register event OrderShippedEvent
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getProductId() {
        return productId;
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
}
