package uptc.edu.co.inventoryservice.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uptc.edu.co.inventoryservice.infrastructure.entities.InventoryEntity;
import java.util.Optional;

public interface JpaInventoryRepository extends JpaRepository<InventoryEntity, Long> {
    Optional<InventoryEntity> findByProductName(String productName);
}