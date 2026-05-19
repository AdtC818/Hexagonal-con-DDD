package uptc.edu.co.inventoryservice.application.ports.in;

import uptc.edu.co.inventoryservice.domain.model.Inventory;
import java.util.List;
import java.util.Optional;

public interface FindInventoryUseCase {
    List<Inventory> findAll();
    Optional<Inventory> findById(Long id);
    Optional<Inventory> findByProductName(String productName);
}