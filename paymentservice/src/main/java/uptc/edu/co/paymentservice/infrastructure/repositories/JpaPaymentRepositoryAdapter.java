package uptc.edu.co.paymentservice.infrastructure.repositories;

import org.springframework.stereotype.Component;
import uptc.edu.co.paymentservice.application.ports.out.PaymentRepositoryPort;
import uptc.edu.co.paymentservice.domain.model.Payment;
import uptc.edu.co.paymentservice.infrastructure.entities.PaymentEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JpaPaymentRepositoryAdapter implements PaymentRepositoryPort {

    private final JpaPaymentRepository jpaPaymentRepository;

    public JpaPaymentRepositoryAdapter(JpaPaymentRepository jpaPaymentRepository) {
        this.jpaPaymentRepository = jpaPaymentRepository;
    }

    @Override
    public Payment save(Payment payment) {
        PaymentEntity entity = jpaPaymentRepository.save(PaymentEntity.fromDomainModel(payment));
        return entity.toDomainModel();
    }

    @Override
    public Optional<Payment> findById(Long id) {
        return jpaPaymentRepository.findById(id).map(PaymentEntity::toDomainModel);
    }

    @Override
    public Optional<Payment> findByOrderId(Long orderId) {
        return jpaPaymentRepository.findByOrderId(orderId).map(PaymentEntity::toDomainModel);
    }

    @Override
    public List<Payment> findAll() {
        return jpaPaymentRepository.findAll().stream()
                .map(PaymentEntity::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpaPaymentRepository.deleteById(id);
    }
}
