package co.edu.uptc.orderservice.application.usecase;

import co.edu.uptc.orderservice.domain.model.Order;
import co.edu.uptc.orderservice.domain.repository.OrderRepository;
import co.edu.uptc.orderservice.domain.valueobject.OrderId;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CancelOrderUseCase {

    private final OrderRepository orderRepository;

    public CancelOrderUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void execute(String orderIdString) {
        OrderId orderId = OrderId.of(orderIdString);
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        
        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            order.cancel();
            orderRepository.save(order);
            System.out.println("[ORDER USE CASE] Orden " + orderIdString + " -> status changed to CANCELLED (compensaciÃ³n)");
        } else {
            System.out.println("[ORDER USE CASE] WARN: no se encontrÃ³ orden con id=" + orderIdString);
        }
    }
}
