package uptc.edu.co.inventoryservice.domain.model;

public class Inventory {
    private Long id;
    private String productName;
    private Integer stock;

    public Inventory() {}

    public Inventory(Long id, String productName, Integer stock) {
        this.id = id;
        this.productName = productName;
        this.stock = stock;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
}