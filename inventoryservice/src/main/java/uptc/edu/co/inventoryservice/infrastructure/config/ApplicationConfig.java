package uptc.edu.co.inventoryservice.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uptc.edu.co.inventoryservice.application.ports.out.InventoryRepositoryPort;
import uptc.edu.co.inventoryservice.application.services.InventoryService;
import uptc.edu.co.inventoryservice.application.usecases.*;
import uptc.edu.co.inventoryservice.application.ports.in.*;

@Configuration
public class ApplicationConfig {

    @Bean
    public InventoryService inventoryService(InventoryRepositoryPort inventoryRepositoryPort) {
        return new InventoryService(inventoryRepositoryPort);
    }

    @Bean
    public CreateInventoryUseCase createInventoryUseCase(InventoryRepositoryPort inventoryRepositoryPort) {
        return new CreateInventoryUseCaseImpl(inventoryRepositoryPort);
    }

    @Bean
    public FindInventoryUseCase findInventoryUseCase(InventoryRepositoryPort inventoryRepositoryPort) {
        return new FindInventoryUseCaseImpl(inventoryRepositoryPort);
    }

    @Bean
    public UpdateInventoryUseCase updateInventoryUseCase(InventoryRepositoryPort inventoryRepositoryPort) {
        return new UpdateInventoryUseCaseImpl(inventoryRepositoryPort);
    }

    @Bean
    public DeleteInventoryUseCase deleteInventoryUseCase(InventoryRepositoryPort inventoryRepositoryPort) {
        return new DeleteInventoryUseCaseImpl(inventoryRepositoryPort);
    }
}