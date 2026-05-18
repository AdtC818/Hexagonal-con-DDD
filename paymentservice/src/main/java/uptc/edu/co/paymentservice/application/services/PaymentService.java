package uptc.edu.co.paymentservice.application.services;

import uptc.edu.co.paymentservice.application.ports.in.CreatePaymentUseCase;
import uptc.edu.co.paymentservice.application.ports.in.DeletePaymentUseCase;
import uptc.edu.co.paymentservice.application.ports.in.FindPaymentsUseCase;
import uptc.edu.co.paymentservice.application.ports.in.UpdatePaymentUseCase;
import uptc.edu.co.paymentservice.domain.model.OrderDTO;
import uptc.edu.co.paymentservice.domain.model.Payment;
import java.util.List;
import java.util.Optional;

public class PaymentService implements CreatePaymentUseCase, FindPaymentsUseCase, UpdatePaymentUseCase, DeletePaymentUseCase {

    private final CreatePaymentUseCase createUseCase;
    private final FindPaymentsUseCase findUseCase;
    private final UpdatePaymentUseCase updateUseCase;
    private final DeletePaymentUseCase deleteUseCase;

    public PaymentService(CreatePaymentUseCase createUseCase, FindPaymentsUseCase findUseCase,
                          UpdatePaymentUseCase updateUseCase, DeletePaymentUseCase deleteUseCase) {
        this.createUseCase = createUseCase;
        this.findUseCase = findUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @Override
    public Payment createPayment(OrderDTO order, String status) {
        return createUseCase.createPayment(order, status);
    }

    @Override
    public Payment createPayment(Payment payment) {
        return createUseCase.createPayment(payment);
    }

    @Override
    public List<Payment> findAllPayments() {
        return findUseCase.findAllPayments();
    }

    @Override
    public Optional<Payment> findPaymentByOrderId(Long orderId) {
        return findUseCase.findPaymentByOrderId(orderId);
    }

    @Override
    public Payment updatePaymentStatus(Long orderId, String newStatus) {
        return updateUseCase.updatePaymentStatus(orderId, newStatus);
    }

    @Override
    public void deletePayment(Long id) {
        deleteUseCase.deletePayment(id);
    }
}
