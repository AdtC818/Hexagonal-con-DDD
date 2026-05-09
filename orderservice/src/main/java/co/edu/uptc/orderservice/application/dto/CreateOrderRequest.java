package co.edu.uptc.orderservice.application.dto;

public record CreateOrderRequest(
        String customerId,
        String productId,
        int quantity,
        double totalAmount
) {
}
