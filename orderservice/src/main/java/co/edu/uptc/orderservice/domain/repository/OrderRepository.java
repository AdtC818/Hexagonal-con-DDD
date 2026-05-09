package co.edu.uptc.orderservice.domain.repository;

import co.edu.uptc.orderservice.domain.model.Order;
import co.edu.uptc.orderservice.domain.valueobject.OrderId;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);
    Optional<Order> findById(OrderId id);
    List<Order> findAll();
}
