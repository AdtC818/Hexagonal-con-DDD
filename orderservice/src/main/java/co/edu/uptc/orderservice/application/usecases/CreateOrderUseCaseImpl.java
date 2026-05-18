package co.edu.uptc.orderservice.application.usecases;

import co.edu.uptc.orderservice.application.ports.in.CreateOrderUseCase;
import co.edu.uptc.orderservice.application.ports.out.OrderRepositoryPort;
import co.edu.uptc.orderservice.domain.model.Order;
import co.edu.uptc.orderservice.shared.domain.EventBus;

public class CreateOrderUseCaseImpl implements CreateOrderUseCase {

    private final OrderRepositoryPort orderRepositoryPort;
    private final EventBus eventBus;

    public CreateOrderUseCaseImpl(OrderRepositoryPort orderRepositoryPort, EventBus eventBus) {
        this.orderRepositoryPort = orderRepositoryPort;
        this.eventBus = eventBus;
    }

    @Override
    public Order createOrder(Order order) {
        Order newOrder = Order.create(
                order.getCustomerId(),
                order.getItems(),
                order.getPaymentId(),
                order.getShipmentId(),
                order.getInventoryReservationId(),
                order.getQuantity(),
                order.getTotalAmount()
        );
        Order savedOrder = orderRepositoryPort.save(newOrder);
        eventBus.publish(newOrder.pullDomainEvents());
        return savedOrder;
    }
}
