package uptc.edu.co.inventoryservice.application.ports.in;

import uptc.edu.co.inventoryservice.domain.model.Inventory;

public interface CreateInventoryUseCase {
    Inventory createInventory(Inventory inventory);
}