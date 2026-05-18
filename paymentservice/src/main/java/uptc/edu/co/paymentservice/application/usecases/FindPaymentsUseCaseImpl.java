package uptc.edu.co.paymentservice.application.usecases;

import uptc.edu.co.paymentservice.application.ports.in.FindPaymentsUseCase;
import uptc.edu.co.paymentservice.application.ports.out.PaymentRepositoryPort;
import uptc.edu.co.paymentservice.domain.model.Payment;
import java.util.List;
import java.util.Optional;

public class FindPaymentsUseCaseImpl implements FindPaymentsUseCase {

    private final PaymentRepositoryPort paymentRepositoryPort;

    public FindPaymentsUseCaseImpl(PaymentRepositoryPort paymentRepositoryPort) {
        this.paymentRepositoryPort = paymentRepositoryPort;
    }

    @Override
    public List<Payment> findAllPayments() {
        return paymentRepositoryPort.findAll();
    }

    @Override
    public Optional<Payment> findPaymentByOrderId(Long orderId) {
        return paymentRepositoryPort.findByOrderId(orderId);
    }
}
