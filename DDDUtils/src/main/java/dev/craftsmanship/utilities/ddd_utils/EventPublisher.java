package dev.craftsmanship.utilities.ddd_utils;

public interface EventPublisher {
    void publish(DomainEvent event);
}
