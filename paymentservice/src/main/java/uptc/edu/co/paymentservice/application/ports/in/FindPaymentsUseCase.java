package uptc.edu.co.paymentservice.application.ports.in;

import uptc.edu.co.paymentservice.domain.model.Payment;
import java.util.List;
import java.util.Optional;

public interface FindPaymentsUseCase {
    List<Payment> findAllPayments();
    Optional<Payment> findPaymentByOrderId(Long orderId);
}
