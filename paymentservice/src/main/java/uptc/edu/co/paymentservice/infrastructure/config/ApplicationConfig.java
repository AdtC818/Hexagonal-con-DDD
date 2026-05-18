package uptc.edu.co.paymentservice.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uptc.edu.co.paymentservice.application.ports.in.CreatePaymentUseCase;
import uptc.edu.co.paymentservice.application.ports.in.DeletePaymentUseCase;
import uptc.edu.co.paymentservice.application.ports.in.FindPaymentsUseCase;
import uptc.edu.co.paymentservice.application.ports.in.UpdatePaymentUseCase;
import uptc.edu.co.paymentservice.application.ports.out.PaymentRepositoryPort;
import uptc.edu.co.paymentservice.application.services.PaymentService;
import uptc.edu.co.paymentservice.application.usecases.CreatePaymentUseCaseImpl;
import uptc.edu.co.paymentservice.application.usecases.DeletePaymentUseCaseImpl;
import uptc.edu.co.paymentservice.application.usecases.FindPaymentsUseCaseImpl;
import uptc.edu.co.paymentservice.application.usecases.UpdatePaymentUseCaseImpl;

@Configuration
public class ApplicationConfig {

    @Bean
    public CreatePaymentUseCase createPaymentUseCase(PaymentRepositoryPort repositoryPort) {
        return new CreatePaymentUseCaseImpl(repositoryPort);
    }

    @Bean
    public FindPaymentsUseCase findPaymentsUseCase(PaymentRepositoryPort repositoryPort) {
        return new FindPaymentsUseCaseImpl(repositoryPort);
    }

    @Bean
    public UpdatePaymentUseCase updatePaymentUseCase(PaymentRepositoryPort repositoryPort) {
        return new UpdatePaymentUseCaseImpl(repositoryPort);
    }

    @Bean
    public DeletePaymentUseCase deletePaymentUseCase(PaymentRepositoryPort repositoryPort) {
        return new DeletePaymentUseCaseImpl(repositoryPort);
    }

    @Bean
    public PaymentService paymentService(
            CreatePaymentUseCase createPaymentUseCase,
            FindPaymentsUseCase findPaymentsUseCase,
            UpdatePaymentUseCase updatePaymentUseCase,
            DeletePaymentUseCase deletePaymentUseCase) {
        return new PaymentService(createPaymentUseCase, findPaymentsUseCase, updatePaymentUseCase, deletePaymentUseCase);
    }
}
