package co.edu.uptc.orderservice.shared.domain;

import java.util.List;

public interface EventBus {
    void publish(DomainEvent event);
    void publish(List<DomainEvent> events);
}
