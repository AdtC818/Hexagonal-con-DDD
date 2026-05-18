package co.edu.uptc.orderservice.infrastructure.repositories;

import co.edu.uptc.orderservice.application.ports.out.OrderRepositoryPort;
import co.edu.uptc.orderservice.domain.model.Order;
import co.edu.uptc.orderservice.infrastructure.entities.OrderEntity;

import java.util.List;
import java.util.Optional;

public class JpaOrderRepositoryAdapter implements OrderRepositoryPort {

    private final JpaOrderRepository jpaOrderRepository;

    public JpaOrderRepositoryAdapter(JpaOrderRepository jpaOrderRepository) {
        this.jpaOrderRepository = jpaOrderRepository;
    }

    @Override
    public Order save(Order order) {
        return jpaOrderRepository.save(OrderEntity.fromDomainModel(order)).toDomainModel();
    }

    @Override
    public Optional<Order> findById(String id) {
        return jpaOrderRepository.findById(id).map(OrderEntity::toDomainModel);
    }

    @Override
    public List<Order> findAll() {
        return jpaOrderRepository.findAll()
                .stream()
                .map(OrderEntity::toDomainModel)
                .toList();
    }

    @Override
    public Order update(Order order) {
        return save(order);
    }

    @Override
    public void deleteById(String id) {
        jpaOrderRepository.deleteById(id);
    }
}
