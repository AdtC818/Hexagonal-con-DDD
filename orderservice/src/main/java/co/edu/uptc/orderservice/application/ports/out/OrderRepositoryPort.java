package co.edu.uptc.orderservice.application.ports.out;

import co.edu.uptc.orderservice.domain.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepositoryPort {
    Order save(Order order);

    Optional<Order> findById(String id);

    List<Order> findAll();

    Order update(Order order);

    void deleteById(String id);
}
