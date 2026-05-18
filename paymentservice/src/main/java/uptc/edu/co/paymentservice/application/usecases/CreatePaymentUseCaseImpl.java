package uptc.edu.co.paymentservice.application.usecases;

import uptc.edu.co.paymentservice.application.ports.in.CreatePaymentUseCase;
import uptc.edu.co.paymentservice.application.ports.out.PaymentRepositoryPort;
import uptc.edu.co.paymentservice.domain.model.OrderDTO;
import uptc.edu.co.paymentservice.domain.model.Payment;

public class CreatePaymentUseCaseImpl implements CreatePaymentUseCase {

    private final PaymentRepositoryPort paymentRepositoryPort;

    public CreatePaymentUseCaseImpl(PaymentRepositoryPort paymentRepositoryPort) {
        this.paymentRepositoryPort = paymentRepositoryPort;
    }

    @Override
    public Payment createPayment(OrderDTO order, String status) {
        Payment payment = new Payment();
        payment.setOrderId(order.getOrderId());
        payment.setAmount(order.getAmount());
        payment.setStatus(status);
        return paymentRepositoryPort.save(payment);
    }

    @Override
    public Payment createPayment(Payment payment) {
        return paymentRepositoryPort.save(payment);
    }
}
