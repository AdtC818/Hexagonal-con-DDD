package co.edu.uptc.shippingservice.application.usecases;

import co.edu.uptc.shippingservice.application.ports.in.FindShippingUseCase;
import co.edu.uptc.shippingservice.application.ports.out.ShippingRepositoryPort;
import co.edu.uptc.shippingservice.domain.model.Shipping;
import java.util.List;
import java.util.Optional;

public class FindShippingUseCaseImpl implements FindShippingUseCase {

    private final ShippingRepositoryPort repositoryPort;

    public FindShippingUseCaseImpl(ShippingRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public List<Shipping> findAll() {
        return repositoryPort.findAll();
    }

    @Override
    public Optional<Shipping> findById(Long id) {
        return repositoryPort.findById(id);
    }
}