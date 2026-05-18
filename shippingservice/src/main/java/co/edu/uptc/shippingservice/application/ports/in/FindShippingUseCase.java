package co.edu.uptc.shippingservice.application.ports.in;

import co.edu.uptc.shippingservice.domain.model.Shipping;
import java.util.List;
import java.util.Optional;

public interface FindShippingUseCase {
    List<Shipping> findAll();
    Optional<Shipping> findById(Long id);
}