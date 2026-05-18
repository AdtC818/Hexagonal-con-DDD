package co.edu.uptc.shippingservice.application.usecases;

import co.edu.uptc.shippingservice.application.ports.in.CreateShippingUseCase;
import co.edu.uptc.shippingservice.application.ports.out.ShippingRepositoryPort;
import co.edu.uptc.shippingservice.domain.model.Shipping;

public class CreateShippingUseCaseImpl implements CreateShippingUseCase {

    private final ShippingRepositoryPort repositoryPort;

    public CreateShippingUseCaseImpl(ShippingRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public Shipping createShipping(Long orderId, String customerDocument,
            String productName, int quantity) {
        Shipping shipping = new Shipping(orderId, customerDocument,
                productName, quantity);
        Shipping saved = repositoryPort.save(shipping);
        System.out.println("[SHIPPING] Envío registrado: " + saved);
        return saved;
    }
}