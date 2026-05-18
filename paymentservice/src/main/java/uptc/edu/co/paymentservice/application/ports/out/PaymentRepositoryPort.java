package uptc.edu.co.paymentservice.application.ports.out;

import uptc.edu.co.paymentservice.domain.model.Payment;
import java.util.List;
import java.util.Optional;

public interface PaymentRepositoryPort {
    Payment save(Payment payment);
    Optional<Payment> findById(Long id);
    Optional<Payment> findByOrderId(Long orderId);
    List<Payment> findAll();
    void deleteById(Long id);
}
