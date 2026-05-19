package uptc.edu.co.inventoryservice.application.usecases;

import uptc.edu.co.inventoryservice.application.ports.in.DeleteInventoryUseCase;
import uptc.edu.co.inventoryservice.application.ports.out.InventoryRepositoryPort;

public class DeleteInventoryUseCaseImpl implements DeleteInventoryUseCase {

    private final InventoryRepositoryPort inventoryRepositoryPort;

    public DeleteInventoryUseCaseImpl(InventoryRepositoryPort inventoryRepositoryPort) {
        this.inventoryRepositoryPort = inventoryRepositoryPort;
    }

    @Override
    public void deleteInventory(Long id) {
        inventoryRepositoryPort.deleteById(id);
    }
}