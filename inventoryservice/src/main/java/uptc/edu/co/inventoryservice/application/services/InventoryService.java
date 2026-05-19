package uptc.edu.co.inventoryservice.application.services;

import uptc.edu.co.inventoryservice.application.ports.in.*;
import uptc.edu.co.inventoryservice.application.ports.out.InventoryRepositoryPort;
import uptc.edu.co.inventoryservice.domain.model.Inventory;
import java.util.List;
import java.util.Optional;

public class InventoryService implements
        CreateInventoryUseCase,
        FindInventoryUseCase,
        UpdateInventoryUseCase,
        DeleteInventoryUseCase {

    private final InventoryRepositoryPort inventoryRepositoryPort;

    public InventoryService(InventoryRepositoryPort inventoryRepositoryPort) {
        this.inventoryRepositoryPort = inventoryRepositoryPort;
    }

    @Override
    public Inventory createInventory(Inventory inventory) {
        return inventoryRepositoryPort.save(inventory);
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

    @Override
    public Inventory updateInventory(Long id, Inventory inventory) {
        inventory.setId(id);
        return inventoryRepositoryPort.update(inventory);
    }

    @Override
    public void deleteInventory(Long id) {
        inventoryRepositoryPort.deleteById(id);
    }

    // Método especial para el flujo Kafka (usado por InventoryConsumer)
    public boolean checkAndReduceStock(String productName, int quantity) {
        return inventoryRepositoryPort.findByProductName(productName)
                .map(item -> {
                    if (item.getStock() >= quantity) {
                        item.setStock(item.getStock() - quantity);
                        inventoryRepositoryPort.update(item);
                        System.out.println("[DB] Stock reducido para: " + productName);
                        return true;
                    }
                    return false;
                }).orElse(false);
    }
}