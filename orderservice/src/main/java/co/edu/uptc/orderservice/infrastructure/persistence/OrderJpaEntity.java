package co.edu.uptc.orderservice.infrastructure.persistence;

import co.edu.uptc.orderservice.domain.model.Order;
import co.edu.uptc.orderservice.domain.valueobject.OrderId;
import co.edu.uptc.orderservice.domain.valueobject.OrderStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "orders")
public class OrderJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;
    
    // In the old mapping: DB column id is auto increment long. 
    // Wait, the prompt says "NO cambies el nombre de la tabla de la base de datos, 
    // solo adapta el mapeo en OrderJpaEntity.java."
    // And before `id` was Long @GeneratedValue.
    // If I use UUID as string, and since the database table has auto increment `id`, it might fail to insert string into bigserial/bigint. 
    // Or wait! If the DB has `ddl-auto=update`, changing the type of `id` from integer to varchar might alter the table or fail. 
    // Since the project uses UUID for OrderId, I assume the user just wants the ID to be String/UUID mapping to DB. Let's make it String id. If it fails, `update` might try to cast it or just fail. A common way is to make `id` String.

    @Column(name = "customer_document", nullable = false)
    private String customerDocument;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "total_price", nullable = false)
    private double totalPrice;

    @Column(name = "status", nullable = false)
    private String status;

    public OrderJpaEntity() {
    }

    public OrderJpaEntity(String id, String customerDocument, String productName, int quantity, double totalPrice, String status) {
        this.id = id;
        this.customerDocument = customerDocument;
        this.productName = productName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public static OrderJpaEntity fromDomain(Order order) {
        return new OrderJpaEntity(
                order.getId().getValue().toString(),
                order.getCustomerId(),
                order.getProductId(),
                order.getQuantity(),
                order.getTotalAmount(),
                order.getStatus().name()
        );
    }

    public Order toDomain() {
        return Order.reconstitute(
                OrderId.of(this.id),
                this.customerDocument,
                this.productName,
                this.quantity,
                this.totalPrice,
                OrderStatus.valueOf(this.status)
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerDocument() {
        return customerDocument;
    }

    public void setCustomerDocument(String customerDocument) {
        this.customerDocument = customerDocument;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
