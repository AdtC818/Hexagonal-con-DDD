package co.edu.uptc.orderservice.domain.valueobject;

public enum OrderStatus {
    PENDING,
    CONFIRMED,
    PAYMENT_PROCESSED,
    INVENTORY_RESERVED,
    SHIPPED,
    CANCELLED
}
