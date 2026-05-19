package uptc.edu.co.inventoryservice.application.usecases;

import uptc.edu.co.inventoryservice.application.ports.in.CreateInventoryUseCase;
import uptc.edu.co.inventoryservice.application.ports.out.InventoryRepositoryPort;
import uptc.edu.co.inventoryservice.domain.model.Inventory;

public class CreateInventoryUseCaseImpl implements CreateInventoryUseCase {

    private final InventoryRepositoryPort inventoryRepositoryPort;

    public CreateInventoryUseCaseImpl(InventoryRepositoryPort inventoryRepositoryPort) {
        this.inventoryRepositoryPort = inventoryRepositoryPort;
    }

    @Override
    public Inventory createInventory(Inventory inventory) {
        return inventoryRepositoryPort.save(inventory);
    }
}