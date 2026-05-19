package uptc.edu.co.inventoryservice.infrastructure.repositories;

import org.springframework.stereotype.Component;
import uptc.edu.co.inventoryservice.application.ports.out.InventoryRepositoryPort;
import uptc.edu.co.inventoryservice.domain.model.Inventory;
import uptc.edu.co.inventoryservice.infrastructure.entities.InventoryEntity;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JpaInventoryRepositoryAdapter implements InventoryRepositoryPort {

    private final JpaInventoryRepository jpaInventoryRepository;

    public JpaInventoryRepositoryAdapter(JpaInventoryRepository jpaInventoryRepository) {
        this.jpaInventoryRepository = jpaInventoryRepository;
    }

    @Override
    public Inventory save(Inventory inventory) {
        InventoryEntity entity = InventoryEntity.fromDomainModel(inventory);
        return jpaInventoryRepository.save(entity).toDomainModel();
    }

    @Override
    public Optional<Inventory> findById(Long id) {
        return jpaInventoryRepository.findById(id).map(InventoryEntity::toDomainModel);
    }

    @Override
    public Optional<Inventory> findByProductName(String productName) {
        return jpaInventoryRepository.findByProductName(productName).map(InventoryEntity::toDomainModel);
    }

    @Override
    public List<Inventory> findAll() {
        return jpaInventoryRepository.findAll()
                .stream()
                .map(InventoryEntity::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public Inventory update(Inventory inventory) {
        InventoryEntity entity = InventoryEntity.fromDomainModel(inventory);
        return jpaInventoryRepository.save(entity).toDomainModel();
    }

    @Override
    public void deleteById(Long id) {
        jpaInventoryRepository.deleteById(id);
    }
}