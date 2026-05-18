package co.edu.uptc.shippingservice.infrastructure.controllers;

import co.edu.uptc.shippingservice.application.ports.in.*;
import co.edu.uptc.shippingservice.domain.model.Shipping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/shipments")
public class ShippingController {

    private final FindShippingUseCase findShippingUseCase;
    private final CreateShippingUseCase createShippingUseCase;
    private final UpdateShippingUseCase updateShippingUseCase;
    private final DeleteShippingUseCase deleteShippingUseCase;

    public ShippingController(FindShippingUseCase findShippingUseCase,
                               CreateShippingUseCase createShippingUseCase,
                               UpdateShippingUseCase updateShippingUseCase,
                               DeleteShippingUseCase deleteShippingUseCase) {
        this.findShippingUseCase = findShippingUseCase;
        this.createShippingUseCase = createShippingUseCase;
        this.updateShippingUseCase = updateShippingUseCase;
        this.deleteShippingUseCase = deleteShippingUseCase;
    }

    @GetMapping
    public ResponseEntity<List<Shipping>> getAll() {
        return ResponseEntity.ok(findShippingUseCase.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shipping> getById(@PathVariable Long id) {
        return findShippingUseCase.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Shipping> create(@RequestBody Shipping shipping) {
        Shipping created = createShippingUseCase.createShipping(
                shipping.getOrderId(),
                shipping.getCustomerDocument(),
                shipping.getProductName(),
                shipping.getQuantity()
        );
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Shipping> update(@PathVariable Long id,
                                            @RequestBody Shipping shipping) {
        return ResponseEntity.ok(
                updateShippingUseCase.updateShipping(id, shipping));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteShippingUseCase.deleteShipping(id);
        return ResponseEntity.noContent().build();
    }
}