package co.edu.uptc.shippingservice.domain.model;

public class Shipping {

    private Long id;
    private Long orderId;
    private String customerDocument;
    private String productName;
    private int quantity;
    private String status;
    private String trackingCode;

    public Shipping() {}

    public Shipping(Long orderId, String customerDocument,
                    String productName, int quantity) {
        this.orderId = orderId;
        this.customerDocument = customerDocument;
        this.productName = productName;
        this.quantity = quantity;
        this.status = "SHIPPED";
        this.trackingCode = "TRK-" + orderId + "-"
                            + System.currentTimeMillis();
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public String getCustomerDocument() { return customerDocument; }
    public void setCustomerDocument(String v) { this.customerDocument = v; }

    public String getProductName() { return productName; }
    public void setProductName(String v) { this.productName = v; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getTrackingCode() { return trackingCode; }
    public void setTrackingCode(String v) { this.trackingCode = v; }
}