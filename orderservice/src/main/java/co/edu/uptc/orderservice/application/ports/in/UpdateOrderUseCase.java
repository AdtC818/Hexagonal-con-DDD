package co.edu.uptc.orderservice.application.ports.in;

import co.edu.uptc.orderservice.domain.model.Order;

import java.util.Optional;

public interface UpdateOrderUseCase {
    Optional<Order> updateOrder(String id, Order order);
}
