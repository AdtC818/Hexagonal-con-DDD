package co.edu.uptc.shippingservice.infrastructure.config;

import co.edu.uptc.shippingservice.application.ports.in.*;
import co.edu.uptc.shippingservice.application.ports.out.ShippingRepositoryPort;
import co.edu.uptc.shippingservice.application.services.ShippingService;
import co.edu.uptc.shippingservice.application.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public ShippingService shippingService(ShippingRepositoryPort port) {
        return new ShippingService(port);
    }

    @Bean
    public CreateShippingUseCase createShippingUseCase(
            ShippingRepositoryPort port) {
        return new CreateShippingUseCaseImpl(port);
    }

    @Bean
    public FindShippingUseCase findShippingUseCase(
            ShippingRepositoryPort port) {
        return new FindShippingUseCaseImpl(port);
    }

    @Bean
    public UpdateShippingUseCase updateShippingUseCase(
            ShippingRepositoryPort port) {
        return new UpdateShippingUseCaseImpl(port);
    }

    @Bean
    public DeleteShippingUseCase deleteShippingUseCase(
            ShippingRepositoryPort port) {
        return new DeleteShippingUseCaseImpl(port);
    }
}