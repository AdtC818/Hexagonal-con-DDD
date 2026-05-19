package uptc.edu.co.inventoryservice.infrastructure.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uptc.edu.co.inventoryservice.application.ports.in.*;
import uptc.edu.co.inventoryservice.domain.model.Inventory;
import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final CreateInventoryUseCase createInventoryUseCase;
    private final FindInventoryUseCase findInventoryUseCase;
    private final UpdateInventoryUseCase updateInventoryUseCase;
    private final DeleteInventoryUseCase deleteInventoryUseCase;

    public InventoryController(CreateInventoryUseCase createInventoryUseCase,
                               FindInventoryUseCase findInventoryUseCase,
                               UpdateInventoryUseCase updateInventoryUseCase,
                               DeleteInventoryUseCase deleteInventoryUseCase) {
        this.createInventoryUseCase = createInventoryUseCase;
        this.findInventoryUseCase = findInventoryUseCase;
        this.updateInventoryUseCase = updateInventoryUseCase;
        this.deleteInventoryUseCase = deleteInventoryUseCase;
    }

    @PostMapping
    public ResponseEntity<Inventory> create(@RequestBody Inventory inventory) {
        return ResponseEntity.ok(createInventoryUseCase.createInventory(inventory));
    }

    @GetMapping
    public ResponseEntity<List<Inventory>> getAll() {
        return ResponseEntity.ok(findInventoryUseCase.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getById(@PathVariable Long id) {
        return findInventoryUseCase.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventory> update(@PathVariable Long id, @RequestBody Inventory inventory) {
        return ResponseEntity.ok(updateInventoryUseCase.updateInventory(id, inventory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteInventoryUseCase.deleteInventory(id);
        return ResponseEntity.noContent().build();
    }
}