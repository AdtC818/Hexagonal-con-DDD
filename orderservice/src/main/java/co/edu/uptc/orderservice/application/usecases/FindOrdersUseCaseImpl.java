package co.edu.uptc.orderservice.application.usecases;

import co.edu.uptc.orderservice.application.ports.in.FindOrdersUseCase;
import co.edu.uptc.orderservice.application.ports.out.OrderRepositoryPort;
import co.edu.uptc.orderservice.domain.model.Order;

import java.util.List;
import java.util.Optional;

public class FindOrdersUseCaseImpl implements FindOrdersUseCase {

    private final OrderRepositoryPort orderRepositoryPort;

    public FindOrdersUseCaseImpl(OrderRepositoryPort orderRepositoryPort) {
        this.orderRepositoryPort = orderRepositoryPort;
    }

    @Override
    public Optional<Order> findOrderById(String id) {
        return orderRepositoryPort.findById(id);
    }

    @Override
    public List<Order> findAllOrders() {
        return orderRepositoryPort.findAll();
    }
}
