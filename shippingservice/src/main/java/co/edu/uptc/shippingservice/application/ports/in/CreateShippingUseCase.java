package co.edu.uptc.shippingservice.application.ports.in;

import co.edu.uptc.shippingservice.domain.model.Shipping;

public interface CreateShippingUseCase {
    Shipping createShipping(Long orderId,
            String customerDocument,
            String productName,
            int quantity);
}