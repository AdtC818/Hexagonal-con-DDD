package co.edu.uptc.shippingservice.infrastructure.repositories;

import co.edu.uptc.shippingservice.infrastructure.entities.ShippingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaShippingRepository
        extends JpaRepository<ShippingEntity, Long> {
}