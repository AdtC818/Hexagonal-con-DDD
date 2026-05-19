package uptc.edu.co.inventoryservice.infrastructure.entities;

import jakarta.persistence.*;
import uptc.edu.co.inventoryservice.domain.model.Inventory;

@Entity
@Table(name = "inventory")
public class InventoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private Integer stock;

    public static InventoryEntity fromDomainModel(Inventory inventory) {
        InventoryEntity entity = new InventoryEntity();
        entity.setId(inventory.getId());
        entity.setProductName(inventory.getProductName());
        entity.setStock(inventory.getStock());
        return entity;
    }

    public Inventory toDomainModel() {
        return new Inventory(this.id, this.productName, this.stock);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
}