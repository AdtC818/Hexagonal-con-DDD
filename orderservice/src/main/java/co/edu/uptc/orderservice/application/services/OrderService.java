package co.edu.uptc.orderservice.application.services;

import co.edu.uptc.orderservice.application.ports.in.CreateOrderUseCase;
import co.edu.uptc.orderservice.application.ports.in.DeleteOrderUseCase;
import co.edu.uptc.orderservice.application.ports.in.FindOrdersUseCase;
import co.edu.uptc.orderservice.application.ports.in.UpdateOrderUseCase;
import co.edu.uptc.orderservice.domain.model.Order;

import java.util.List;
import java.util.Optional;

public class OrderService implements CreateOrderUseCase, FindOrdersUseCase, UpdateOrderUseCase, DeleteOrderUseCase {

    private final CreateOrderUseCase createOrderUseCase;
    private final FindOrdersUseCase findOrdersUseCase;
    private final UpdateOrderUseCase updateOrderUseCase;
    private final DeleteOrderUseCase deleteOrderUseCase;

    public OrderService(CreateOrderUseCase createOrderUseCase, FindOrdersUseCase findOrdersUseCase,
                        UpdateOrderUseCase updateOrderUseCase, DeleteOrderUseCase deleteOrderUseCase) {
        this.createOrderUseCase = createOrderUseCase;
        this.findOrdersUseCase = findOrdersUseCase;
        this.updateOrderUseCase = updateOrderUseCase;
        this.deleteOrderUseCase = deleteOrderUseCase;
    }

    @Override
    public Order createOrder(Order order) {
        return createOrderUseCase.createOrder(order);
    }

    @Override
    public Optional<Order> findOrderById(String id) {
        return findOrdersUseCase.findOrderById(id);
    }

    @Override
    public List<Order> findAllOrders() {
        return findOrdersUseCase.findAllOrders();
    }

    @Override
    public Optional<Order> updateOrder(String id, Order order) {
        return updateOrderUseCase.updateOrder(id, order);
    }

    @Override
    public boolean deleteOrder(String id) {
        return deleteOrderUseCase.deleteOrder(id);
    }
}
