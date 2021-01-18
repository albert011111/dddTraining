package com.kruczek.event;

import java.util.Objects;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.kruczek.DomainEvent;
import com.kruczek.DomainEventPublisher;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DomainEventPublisherExecutor implements DomainEventPublisher {
	private final ApplicationEventPublisher appPublisher;

	@Override
	public void publish(DomainEvent event) {
		appPublisher.publishEvent(Objects.requireNonNull(event, "event can't be null"));
	}
}
