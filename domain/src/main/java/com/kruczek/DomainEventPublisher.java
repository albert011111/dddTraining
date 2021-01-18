package com.kruczek;

public interface DomainEventPublisher {
	void publish(DomainEvent event);
}
