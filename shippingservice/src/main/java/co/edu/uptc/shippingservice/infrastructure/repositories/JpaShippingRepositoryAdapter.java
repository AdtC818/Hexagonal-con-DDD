package co.edu.uptc.shippingservice.infrastructure.repositories;

import co.edu.uptc.shippingservice.application.ports.out.ShippingRepositoryPort;
import co.edu.uptc.shippingservice.domain.model.Shipping;
import co.edu.uptc.shippingservice.infrastructure.entities.ShippingEntity;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JpaShippingRepositoryAdapter implements ShippingRepositoryPort {

    private final JpaShippingRepository jpaRepository;

    public JpaShippingRepositoryAdapter(JpaShippingRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Shipping save(Shipping shipping) {
        ShippingEntity entity = ShippingEntity.fromDomainModel(shipping);
        return jpaRepository.save(entity).toDomainModel();
    }

    @Override
    public Optional<Shipping> findById(Long id) {
        return jpaRepository.findById(id)
                .map(ShippingEntity::toDomainModel);
    }

    @Override
    public List<Shipping> findAll() {
        return jpaRepository.findAll().stream()
                .map(ShippingEntity::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public Shipping update(Shipping shipping) {
        ShippingEntity entity = ShippingEntity.fromDomainModel(shipping);
        return jpaRepository.save(entity).toDomainModel();
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}