package uptc.edu.co.inventoryservice.application.usecases;

import uptc.edu.co.inventoryservice.application.ports.in.FindInventoryUseCase;
import uptc.edu.co.inventoryservice.application.ports.out.InventoryRepositoryPort;
import uptc.edu.co.inventoryservice.domain.model.Inventory;
import java.util.List;
import java.util.Optional;

public class FindInventoryUseCaseImpl implements FindInventoryUseCase {

    private final InventoryRepositoryPort inventoryRepositoryPort;

    public FindInventoryUseCaseImpl(InventoryRepositoryPort inventoryRepositoryPort) {
        this.inventoryRepositoryPort = inventoryRepositoryPort;
    }

    @Override
    public List<Inventory> findAll() {
        return inventoryRepositoryPort.findAll();
    }

    @Override
    public Optional<Inventory> findById(Long id) {
        return inventoryRepositoryPort.findById(id);
    }

    @Override
    public Optional<Inventory> findByProductName(String productName) {
        return inventoryRepositoryPort.findByProductName(productName);
    }
}