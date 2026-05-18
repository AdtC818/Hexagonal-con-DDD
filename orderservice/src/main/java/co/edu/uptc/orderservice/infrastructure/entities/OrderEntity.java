package co.edu.uptc.orderservice.infrastructure.entities;

import co.edu.uptc.orderservice.domain.model.Order;
import co.edu.uptc.orderservice.domain.valueobject.OrderId;
import co.edu.uptc.orderservice.domain.valueobject.OrderStatus;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
    @Column(name = "item_id")
    private List<String> items = new ArrayList<>();

    @Column(name = "customer_id", nullable = false)
    private String customerId;

    @Column(name = "payment_id")
    private String paymentId;

    @Column(name = "shipment_id")
    private String shipmentId;

    @Column(name = "inventory_reservation_id")
    private String inventoryReservationId;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "total_amount", nullable = false)
    private double totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public OrderEntity() {
    }

    public OrderEntity(String id, List<String> items, String customerId, String paymentId, String shipmentId,
                       String inventoryReservationId, int quantity, double totalAmount, OrderStatus status,
                       LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
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

    public static OrderEntity fromDomainModel(Order order) {
        return new OrderEntity(
                order.getId().getValue().toString(),
                order.getItems(),
                order.getCustomerId(),
                order.getPaymentId(),
                order.getShipmentId(),
                order.getInventoryReservationId(),
                order.getQuantity(),
                order.getTotalAmount(),
                order.getStatus(),
                order.getCreatedAt(),
                order.getUpdatedAt()
        );
    }

    public Order toDomainModel() {
        return Order.reconstitute(
                OrderId.of(this.id),
                this.items,
                this.customerId,
                this.paymentId,
                this.shipmentId,
                this.inventoryReservationId,
                this.quantity,
                this.totalAmount,
                this.status,
                this.createdAt,
                this.updatedAt
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
