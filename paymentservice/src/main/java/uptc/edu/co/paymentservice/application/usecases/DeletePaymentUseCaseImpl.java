package uptc.edu.co.paymentservice.application.usecases;

import uptc.edu.co.paymentservice.application.ports.in.DeletePaymentUseCase;
import uptc.edu.co.paymentservice.application.ports.out.PaymentRepositoryPort;

public class DeletePaymentUseCaseImpl implements DeletePaymentUseCase {

    private final PaymentRepositoryPort paymentRepositoryPort;

    public DeletePaymentUseCaseImpl(PaymentRepositoryPort paymentRepositoryPort) {
        this.paymentRepositoryPort = paymentRepositoryPort;
    }

    @Override
    public void deletePayment(Long id) {
        paymentRepositoryPort.deleteById(id);
    }
}
