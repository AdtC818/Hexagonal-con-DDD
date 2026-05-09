package co.edu.uptc.orderservice.application.usecase;

import co.edu.uptc.orderservice.application.dto.OrderResponse;
import co.edu.uptc.orderservice.domain.model.Order;
import co.edu.uptc.orderservice.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListOrdersUseCase {

    private final OrderRepository orderRepository;

    public ListOrdersUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderResponse> execute() {
        List<Order> orders = orderRepository.findAll();
        
        return orders.stream()
                .map(order -> new OrderResponse(
                        order.getId().getValue().toString(),
                        order.getStatus().name(),
                        order.getCustomerId(),
                        order.getProductId(),
                        order.getTotalAmount(),
                        ""
                ))
                .collect(Collectors.toList());
    }
}
