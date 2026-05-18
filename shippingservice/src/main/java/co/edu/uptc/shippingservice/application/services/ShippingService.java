package co.edu.uptc.shippingservice.application.services;

import co.edu.uptc.shippingservice.application.ports.in.*;
import co.edu.uptc.shippingservice.application.ports.out.ShippingRepositoryPort;
import co.edu.uptc.shippingservice.domain.model.Shipping;
import java.util.List;
import java.util.Optional;

public class ShippingService implements CreateShippingUseCase,
        FindShippingUseCase, UpdateShippingUseCase, DeleteShippingUseCase {

    private final ShippingRepositoryPort repositoryPort;

    public ShippingService(ShippingRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public Shipping createShipping(Long orderId, String customerDocument,
            String productName, int quantity) {
        Shipping shipping = new Shipping(orderId, customerDocument,
                productName, quantity);
        Shipping saved = repositoryPort.save(shipping);
        System.out.println("[SHIPPING SERVICE] Envío registrado: " + saved);
        return saved;
    }

    @Override
    public List<Shipping> findAll() {
        return repositoryPort.findAll();
    }

    @Override
    public Optional<Shipping> findById(Long id) {
        return repositoryPort.findById(id);
    }

    @Override
    public Shipping updateShipping(Long id, Shipping shipping) {
        shipping.setId(id);
        return repositoryPort.update(shipping);
    }

    @Override
    public void deleteShipping(Long id) {
        repositoryPort.deleteById(id);
    }
}