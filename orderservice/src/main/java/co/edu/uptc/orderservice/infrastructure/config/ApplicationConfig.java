package co.edu.uptc.orderservice.infrastructure.config;

import co.edu.uptc.orderservice.application.ports.in.CreateOrderUseCase;
import co.edu.uptc.orderservice.application.ports.in.DeleteOrderUseCase;
import co.edu.uptc.orderservice.application.ports.in.FindOrdersUseCase;
import co.edu.uptc.orderservice.application.ports.in.UpdateOrderUseCase;
import co.edu.uptc.orderservice.application.ports.out.OrderRepositoryPort;
import co.edu.uptc.orderservice.application.services.OrderService;
import co.edu.uptc.orderservice.application.usecases.CreateOrderUseCaseImpl;
import co.edu.uptc.orderservice.application.usecases.DeleteOrderUseCaseImpl;
import co.edu.uptc.orderservice.application.usecases.FindOrdersUseCaseImpl;
import co.edu.uptc.orderservice.application.usecases.UpdateOrderUseCaseImpl;
import co.edu.uptc.orderservice.infrastructure.repositories.JpaOrderRepository;
import co.edu.uptc.orderservice.infrastructure.repositories.JpaOrderRepositoryAdapter;
import co.edu.uptc.orderservice.shared.domain.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public OrderRepositoryPort orderRepositoryPort(JpaOrderRepository jpaOrderRepository) {
        return new JpaOrderRepositoryAdapter(jpaOrderRepository);
    }

    @Bean
    public CreateOrderUseCase createOrderUseCase(OrderRepositoryPort orderRepositoryPort, EventBus eventBus) {
        return new CreateOrderUseCaseImpl(orderRepositoryPort, eventBus);
    }

    @Bean
    public FindOrdersUseCase findOrdersUseCase(OrderRepositoryPort orderRepositoryPort) {
        return new FindOrdersUseCaseImpl(orderRepositoryPort);
    }

    @Bean
    public UpdateOrderUseCase updateOrderUseCase(OrderRepositoryPort orderRepositoryPort) {
        return new UpdateOrderUseCaseImpl(orderRepositoryPort);
    }

    @Bean
    public DeleteOrderUseCase deleteOrderUseCase(OrderRepositoryPort orderRepositoryPort) {
        return new DeleteOrderUseCaseImpl(orderRepositoryPort);
    }

    @Bean
    public OrderService orderService(CreateOrderUseCase createOrderUseCase, FindOrdersUseCase findOrdersUseCase,
                                     UpdateOrderUseCase updateOrderUseCase, DeleteOrderUseCase deleteOrderUseCase) {
        return new OrderService(createOrderUseCase, findOrdersUseCase, updateOrderUseCase, deleteOrderUseCase);
    }
}
