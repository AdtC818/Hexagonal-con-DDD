package co.edu.uptc.orderservice.infrastructure.messaging;

import co.edu.uptc.orderservice.domain.event.OrderCreatedEvent;
import co.edu.uptc.orderservice.shared.domain.DomainEvent;
import co.edu.uptc.orderservice.shared.domain.EventBus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KafkaEventBus implements EventBus {

    private final KafkaOrderEventProducer orderEventProducer;

    public KafkaEventBus(KafkaOrderEventProducer orderEventProducer) {
        this.orderEventProducer = orderEventProducer;
    }

    @Override
    public void publish(DomainEvent event) {
        if (event instanceof OrderCreatedEvent orderCreatedEvent) {
            orderEventProducer.publishOrderCreated(orderCreatedEvent);
        }
        // Handle other domain events as they are added...
    }

    @Override
    public void publish(List<DomainEvent> events) {
        for (DomainEvent event : events) {
            publish(event);
        }
    }
}
