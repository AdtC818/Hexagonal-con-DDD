package co.edu.uptc.orderservice.application.ports.in;

import co.edu.uptc.orderservice.domain.model.Order;

public interface CreateOrderUseCase {
    Order createOrder(Order order);
}
