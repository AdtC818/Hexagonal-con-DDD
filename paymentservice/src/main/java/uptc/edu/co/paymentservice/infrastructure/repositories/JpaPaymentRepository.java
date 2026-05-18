package uptc.edu.co.paymentservice.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uptc.edu.co.paymentservice.infrastructure.entities.PaymentEntity;
import java.util.Optional;

public interface JpaPaymentRepository extends JpaRepository<PaymentEntity, Long> {
    Optional<PaymentEntity> findByOrderId(Long orderId);
}
