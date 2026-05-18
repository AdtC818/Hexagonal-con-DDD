package co.edu.uptc.shippingservice.application.usecases;

import co.edu.uptc.shippingservice.application.ports.in.DeleteShippingUseCase;
import co.edu.uptc.shippingservice.application.ports.out.ShippingRepositoryPort;

public class DeleteShippingUseCaseImpl implements DeleteShippingUseCase {

    private final ShippingRepositoryPort repositoryPort;

    public DeleteShippingUseCaseImpl(ShippingRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public void deleteShipping(Long id) {
        repositoryPort.deleteById(id);
    }
}