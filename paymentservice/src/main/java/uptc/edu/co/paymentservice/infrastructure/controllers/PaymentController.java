package uptc.edu.co.paymentservice.infrastructure.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uptc.edu.co.paymentservice.application.services.PaymentService;
import uptc.edu.co.paymentservice.domain.model.Payment;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/all")
    public List<Payment> getAllPayments() {
        return paymentService.findAllPayments();
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Payment> getPaymentByOrderId(@PathVariable Long orderId) {
        Optional<Payment> payment = paymentService.findPaymentByOrderId(orderId);
        return payment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Payment createPayment(@RequestBody Payment payment) {
        return paymentService.createPayment(payment);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Payment> updatePaymentStatus(@PathVariable Long orderId, @RequestParam String status) {
        Payment updatedPayment = paymentService.updatePaymentStatus(orderId, status);
        if (updatedPayment != null) {
            return ResponseEntity.ok(updatedPayment);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/test")
    public String test() {
        Payment p = new Payment();
        p.setOrderId(1L);
        p.setAmount(100.0);
        p.setStatus("PROBANDO_H2");
        paymentService.createPayment(p);
        return "Registro guardado exitosamente mediante Hexagonal Architecture";
    }
}
