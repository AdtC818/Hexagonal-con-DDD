package co.edu.uptc.orderservice.domain.event;

import co.edu.uptc.orderservice.domain.valueobject.OrderId;
import co.edu.uptc.orderservice.shared.domain.DomainEvent;

public class OrderCreatedEvent extends DomainEvent {
    private final String orderId;
    private final String customerId;
    private final String productId;
    private final int quantity;
    private final double totalAmount;

    public OrderCreatedEvent(OrderId orderId, String customerId, String productId, int quantity, double totalAmount) {
        super();
        this.orderId = orderId.getValue().toString();
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
    }

    public String getOrderId() {
        return orderId;
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
}
