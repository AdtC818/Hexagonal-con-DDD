package co.edu.uptc.shippingservice.application.ports.in;

import co.edu.uptc.shippingservice.domain.model.Shipping;

public interface UpdateShippingUseCase {
    Shipping updateShipping(Long id, Shipping shipping);
}