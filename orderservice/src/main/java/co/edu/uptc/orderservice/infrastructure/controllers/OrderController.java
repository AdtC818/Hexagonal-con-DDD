package co.edu.uptc.orderservice.infrastructure.controllers;

import co.edu.uptc.orderservice.application.services.OrderService;
import co.edu.uptc.orderservice.domain.model.Order;
import co.edu.uptc.orderservice.domain.valueobject.OrderId;
import co.edu.uptc.orderservice.domain.valueobject.OrderStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest request) {
        Order order = request.toDomainModel();
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(order));
    }

    @PostMapping("/create")
    public ResponseEntity<Order> createOrderLegacy(@RequestBody OrderRequest request) {
        return createOrder(request);
    }

    @GetMapping
    public ResponseEntity<List<Order>> findAllOrders() {
        return ResponseEntity.ok(orderService.findAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findOrderById(@PathVariable String id) {
        return orderService.findOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable String id, @RequestBody OrderRequest request) {
        return orderService.updateOrder(id, request.toDomainModel(id))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
        if (!orderService.deleteOrder(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    public record OrderRequest(
            List<String> items,
            String customerId,
            String paymentId,
            String shipmentId,
            String inventoryReservationId,
            int quantity,
            double totalAmount,
            OrderStatus status
    ) {
        private Order toDomainModel() {
            return toDomainModel(OrderId.generate().getValue().toString());
        }

        private Order toDomainModel(String id) {
            return Order.reconstitute(
                    OrderId.of(id),
                    items == null ? List.of() : items,
                    customerId,
                    paymentId,
                    shipmentId,
                    inventoryReservationId,
                    quantity,
                    totalAmount,
                    status == null ? OrderStatus.PENDING : status,
                    null,
                    null
            );
        }
    }
}
