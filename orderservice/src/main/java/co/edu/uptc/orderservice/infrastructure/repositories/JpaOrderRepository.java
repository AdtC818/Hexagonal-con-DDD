package co.edu.uptc.orderservice.infrastructure.repositories;

import co.edu.uptc.orderservice.infrastructure.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepository extends JpaRepository<OrderEntity, String> {
}
