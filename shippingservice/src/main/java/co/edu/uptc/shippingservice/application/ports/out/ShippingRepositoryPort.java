package co.edu.uptc.shippingservice.application.ports.out;

import co.edu.uptc.shippingservice.domain.model.Shipping;
import java.util.List;
import java.util.Optional;

public interface ShippingRepositoryPort {
    Shipping save(Shipping shipping);

    Optional<Shipping> findById(Long id);

    List<Shipping> findAll();

    Shipping update(Shipping shipping);

    void deleteById(Long id);
}