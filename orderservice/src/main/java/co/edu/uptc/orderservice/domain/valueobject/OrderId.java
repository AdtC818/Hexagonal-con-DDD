package co.edu.uptc.orderservice.domain.valueobject;

import co.edu.uptc.orderservice.shared.domain.ValueObject;

import java.util.Objects;
import java.util.UUID;

public class OrderId implements ValueObject {
    private final UUID value;

    private OrderId(UUID value) {
        this.value = value;
    }

    public static OrderId of(UUID value) {
        return new OrderId(value);
    }

    public static OrderId of(String value) {
        return new OrderId(UUID.fromString(value));
    }

    public static OrderId generate() {
        return new OrderId(UUID.randomUUID());
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderId orderId = (OrderId) o;
        return Objects.equals(value, orderId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
