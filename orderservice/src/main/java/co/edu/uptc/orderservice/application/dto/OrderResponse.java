package co.edu.uptc.orderservice.application.dto;

public record OrderResponse(
        String orderId,
        String status,
        String customerId,
        String productId,
        double totalAmount,
        String createdAt
) {
}
