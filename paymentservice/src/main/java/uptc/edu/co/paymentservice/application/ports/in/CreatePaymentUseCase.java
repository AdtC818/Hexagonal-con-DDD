package uptc.edu.co.paymentservice.application.ports.in;

import uptc.edu.co.paymentservice.domain.model.Payment;
import uptc.edu.co.paymentservice.domain.model.OrderDTO;

public interface CreatePaymentUseCase {
    Payment createPayment(OrderDTO order, String status);
    Payment createPayment(Payment payment);
}
