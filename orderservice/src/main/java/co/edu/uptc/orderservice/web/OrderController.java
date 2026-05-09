package co.edu.uptc.orderservice.web;

import co.edu.uptc.orderservice.application.dto.CreateOrderRequest;
import co.edu.uptc.orderservice.application.dto.OrderResponse;
import co.edu.uptc.orderservice.application.usecase.CreateOrderUseCase;
import co.edu.uptc.orderservice.application.usecase.GetOrderUseCase;
import co.edu.uptc.orderservice.application.usecase.ListOrdersUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final GetOrderUseCase getOrderUseCase;
    private final ListOrdersUseCase listOrdersUseCase;

    public OrderController(CreateOrderUseCase createOrderUseCase, GetOrderUseCase getOrderUseCase, ListOrdersUseCase listOrdersUseCase) {
        this.createOrderUseCase = createOrderUseCase;
        this.getOrderUseCase = getOrderUseCase;
        this.listOrdersUseCase = listOrdersUseCase;
    }

    // POST /orders/create
    // Old Body: { "customerDocument":"12345", "productName":"Laptop", "quantity":1, "totalPrice":2500000 }
    @PostMapping("/create")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody CreateOrderRequest request) {
        OrderResponse response = createOrderUseCase.execute(request);
        return ResponseEntity.ok(response);
    }

    // GET /orders  -> ver todos (Ãºtil para verificar estados)
    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        return ResponseEntity.ok(listOrdersUseCase.execute());
    }

    // GET /orders/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable String id) {
        return getOrderUseCase.execute(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
