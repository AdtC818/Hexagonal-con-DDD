package uptc.edu.co.inventoryservice.application.usecases;

import uptc.edu.co.inventoryservice.application.ports.in.UpdateInventoryUseCase;
import uptc.edu.co.inventoryservice.application.ports.out.InventoryRepositoryPort;
import uptc.edu.co.inventoryservice.domain.model.Inventory;

public class UpdateInventoryUseCaseImpl implements UpdateInventoryUseCase {

    private final InventoryRepositoryPort inventoryRepositoryPort;

    public UpdateInventoryUseCaseImpl(InventoryRepositoryPort inventoryRepositoryPort) {
        this.inventoryRepositoryPort = inventoryRepositoryPort;
    }

    @Override
    public Inventory updateInventory(Long id, Inventory inventory) {
        inventory.setId(id);
        return inventoryRepositoryPort.update(inventory);
    }
}