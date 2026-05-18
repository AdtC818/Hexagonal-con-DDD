package co.edu.uptc.orderservice.application.usecases;

import co.edu.uptc.orderservice.application.ports.in.DeleteOrderUseCase;
import co.edu.uptc.orderservice.application.ports.out.OrderRepositoryPort;

public class DeleteOrderUseCaseImpl implements DeleteOrderUseCase {

    private final OrderRepositoryPort orderRepositoryPort;

    public DeleteOrderUseCaseImpl(OrderRepositoryPort orderRepositoryPort) {
        this.orderRepositoryPort = orderRepositoryPort;
    }

    @Override
    public boolean deleteOrder(String id) {
        if (orderRepositoryPort.findById(id).isEmpty()) {
            return false;
        }
        orderRepositoryPort.deleteById(id);
        return true;
    }
}
