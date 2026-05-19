package uptc.edu.co.inventoryservice.application.ports.out;

import uptc.edu.co.inventoryservice.domain.model.Inventory;
import java.util.List;
import java.util.Optional;

public interface InventoryRepositoryPort {
    Inventory save(Inventory inventory);
    Optional<Inventory> findById(Long id);
    Optional<Inventory> findByProductName(String productName);
    List<Inventory> findAll();
    Inventory update(Inventory inventory);
    void deleteById(Long id);
}