package co.edu.uptc.orderservice.infrastructure.persistence;

import co.edu.uptc.orderservice.domain.model.Order;
import co.edu.uptc.orderservice.domain.repository.OrderRepository;
import co.edu.uptc.orderservice.domain.valueobject.OrderId;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository jpaRepository;

    public OrderRepositoryImpl(OrderJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Order save(Order order) {
        OrderJpaEntity entity = OrderJpaEntity.fromDomain(order);
        OrderJpaEntity saved = jpaRepository.save(entity);
        return saved.toDomain();
    }

    @Override
    public Optional<Order> findById(OrderId id) {
        return jpaRepository.findById(id.getValue().toString())
                .map(OrderJpaEntity::toDomain);
    }

    @Override
    public List<Order> findAll() {
        return jpaRepository.findAll().stream()
                .map(OrderJpaEntity::toDomain)
                .collect(Collectors.toList());
    }
}
