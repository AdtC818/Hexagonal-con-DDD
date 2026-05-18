package co.edu.uptc.shippingservice.infrastructure.entities;

import co.edu.uptc.shippingservice.domain.model.Shipping;
import jakarta.persistence.*;

@Entity
@Table(name = "shipments")
public class ShippingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(name = "customer_document", nullable = false)
    private String customerDocument;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "tracking_code")
    private String trackingCode;

    public Shipping toDomainModel() {
        Shipping s = new Shipping();
        s.setId(this.id);
        s.setOrderId(this.orderId);
        s.setCustomerDocument(this.customerDocument);
        s.setProductName(this.productName);
        s.setQuantity(this.quantity);
        s.setStatus(this.status);
        s.setTrackingCode(this.trackingCode);
        return s;
    }

    public static ShippingEntity fromDomainModel(Shipping s) {
        ShippingEntity e = new ShippingEntity();
        e.id = s.getId();
        e.orderId = s.getOrderId();
        e.customerDocument = s.getCustomerDocument();
        e.productName = s.getProductName();
        e.quantity = s.getQuantity();
        e.status = s.getStatus();
        e.trackingCode = s.getTrackingCode();
        return e;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getCustomerDocument() {
        return customerDocument;
    }

    public void setCustomerDocument(String v) {
        customerDocument = v;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String v) {
        productName = v;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTrackingCode() {
        return trackingCode;
    }

    public void setTrackingCode(String v) {
        trackingCode = v;
    }
}