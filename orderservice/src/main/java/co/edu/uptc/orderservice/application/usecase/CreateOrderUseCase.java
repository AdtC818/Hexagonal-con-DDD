package co.edu.uptc.orderservice.application.usecase;

import co.edu.uptc.orderservice.application.dto.CreateOrderRequest;
import co.edu.uptc.orderservice.application.dto.OrderResponse;
import co.edu.uptc.orderservice.domain.model.Order;
import co.edu.uptc.orderservice.domain.repository.OrderRepository;
import co.edu.uptc.orderservice.shared.application.UseCase;
import co.edu.uptc.orderservice.shared.domain.EventBus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CreateOrderUseCase implements UseCase<CreateOrderRequest, OrderResponse> {

    private final OrderRepository orderRepository;
    private final EventBus eventBus;

    public CreateOrderUseCase(OrderRepository orderRepository, EventBus eventBus) {
        this.orderRepository = orderRepository;
        this.eventBus = eventBus;
    }

    @Override
    public OrderResponse execute(CreateOrderRequest input) {
        Order order = Order.create(
                input.customerId(),
                input.productId(),
                input.quantity(),
                input.totalAmount()
        );

        Order savedOrder = orderRepository.save(order);
        eventBus.publish(order.pullDomainEvents());

        return new OrderResponse(
                savedOrder.getId().getValue().toString(),
                savedOrder.getStatus().name(),
                savedOrder.getCustomerId(),
                savedOrder.getProductId(),
                savedOrder.getTotalAmount(),
                LocalDateTime.now().toString()
        );
    }
}
