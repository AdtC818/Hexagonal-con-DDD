package uptc.edu.co.paymentservice.application.usecases;

import uptc.edu.co.paymentservice.application.ports.in.UpdatePaymentUseCase;
import uptc.edu.co.paymentservice.application.ports.out.PaymentRepositoryPort;
import uptc.edu.co.paymentservice.domain.model.Payment;
import java.util.Optional;

public class UpdatePaymentUseCaseImpl implements UpdatePaymentUseCase {

    private final PaymentRepositoryPort paymentRepositoryPort;

    public UpdatePaymentUseCaseImpl(PaymentRepositoryPort paymentRepositoryPort) {
        this.paymentRepositoryPort = paymentRepositoryPort;
    }

    @Override
    public Payment updatePaymentStatus(Long orderId, String newStatus) {
        Optional<Payment> optionalPayment = paymentRepositoryPort.findByOrderId(orderId);
        if (optionalPayment.isPresent()) {
            Payment payment = optionalPayment.get();
            payment.setStatus(newStatus);
            return paymentRepositoryPort.save(payment);
        }
        return null; // Or throw exception as appropriate
    }
}
