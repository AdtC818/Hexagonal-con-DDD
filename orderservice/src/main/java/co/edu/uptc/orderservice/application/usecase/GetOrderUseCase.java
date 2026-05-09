package co.edu.uptc.orderservice.application.usecase;

import co.edu.uptc.orderservice.application.dto.OrderResponse;
import co.edu.uptc.orderservice.domain.model.Order;
import co.edu.uptc.orderservice.domain.repository.OrderRepository;
import co.edu.uptc.orderservice.domain.valueobject.OrderId;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetOrderUseCase {

    private final OrderRepository orderRepository;

    public GetOrderUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Optional<OrderResponse> execute(String orderIdString) {
        OrderId orderId = OrderId.of(orderIdString);
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        
        return orderOpt.map(order -> new OrderResponse(
                order.getId().getValue().toString(),
                order.getStatus().name(),
                order.getCustomerId(),
                order.getProductId(),
                order.getTotalAmount(),
                ""
        ));
    }
}
