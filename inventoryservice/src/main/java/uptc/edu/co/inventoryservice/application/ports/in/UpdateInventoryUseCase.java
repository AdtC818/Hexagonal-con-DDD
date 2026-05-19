package uptc.edu.co.inventoryservice.application.ports.in;

import uptc.edu.co.inventoryservice.domain.model.Inventory;

public interface UpdateInventoryUseCase {
    Inventory updateInventory(Long id, Inventory inventory);
}