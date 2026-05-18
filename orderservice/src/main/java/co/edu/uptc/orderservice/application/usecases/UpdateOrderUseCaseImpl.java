package co.edu.uptc.orderservice.application.usecases;

import co.edu.uptc.orderservice.application.ports.in.UpdateOrderUseCase;
import co.edu.uptc.orderservice.application.ports.out.OrderRepositoryPort;
import co.edu.uptc.orderservice.domain.model.Order;

import java.util.Optional;

public class UpdateOrderUseCaseImpl implements UpdateOrderUseCase {

    private final OrderRepositoryPort orderRepositoryPort;

    public UpdateOrderUseCaseImpl(OrderRepositoryPort orderRepositoryPort) {
        this.orderRepositoryPort = orderRepositoryPort;
    }

    @Override
    public Optional<Order> updateOrder(String id, Order order) {
        return orderRepositoryPort.findById(id)
                .map(existingOrder -> {
                    existingOrder.update(
                            order.getItems(),
                            order.getCustomerId(),
                            order.getPaymentId(),
                            order.getShipmentId(),
                            order.getInventoryReservationId(),
                            order.getQuantity(),
                            order.getTotalAmount(),
                            order.getStatus()
                    );
                    return orderRepositoryPort.update(existingOrder);
                });
    }
}
