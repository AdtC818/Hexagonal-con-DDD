package co.edu.uptc.orderservice.application.ports.in;

import co.edu.uptc.orderservice.domain.model.Order;

import java.util.List;
import java.util.Optional;

public interface FindOrdersUseCase {
    Optional<Order> findOrderById(String id);

    List<Order> findAllOrders();
}
