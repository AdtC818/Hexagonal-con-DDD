package uptc.edu.co.paymentservice.application.ports.in;

import uptc.edu.co.paymentservice.domain.model.Payment;

public interface UpdatePaymentUseCase {
    Payment updatePaymentStatus(Long orderId, String newStatus);
}
