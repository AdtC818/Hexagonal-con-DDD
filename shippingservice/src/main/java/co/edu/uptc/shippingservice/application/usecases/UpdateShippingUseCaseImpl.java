package co.edu.uptc.shippingservice.application.usecases;

import co.edu.uptc.shippingservice.application.ports.in.UpdateShippingUseCase;
import co.edu.uptc.shippingservice.application.ports.out.ShippingRepositoryPort;
import co.edu.uptc.shippingservice.domain.model.Shipping;

public class UpdateShippingUseCaseImpl implements UpdateShippingUseCase {

    private final ShippingRepositoryPort repositoryPort;

    public UpdateShippingUseCaseImpl(ShippingRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public Shipping updateShipping(Long id, Shipping shipping) {
        shipping.setId(id);
        return repositoryPort.update(shipping);
    }
}