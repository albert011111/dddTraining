package com.kruczek.order;

import java.time.Instant;
import java.util.Objects;

import com.kruczek.DomainEventPublisher;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder
class OrderFacade {
	private final OrderRepository orderRepository;
	private final DomainEventPublisher eventPublisher;

	public OrderFacade(OrderRepository orderRepository, DomainEventPublisher eventPublisher) {
		this.orderRepository = Objects.requireNonNull(orderRepository, "orderRepository can't be null");
		this.eventPublisher = Objects.requireNonNull(eventPublisher, "eventPublisher can't be null");
	}

	public OrderDto saveOrder(OrderDto orderDto) {
		final OrderDto savedOrder = OrderFactory.toOrderDto(orderRepository.save(OrderFactory.fromDto(orderDto)));
		final OrderEvent event = new OrderEvent(Instant.now(), savedOrder.getId(), OrderState.fromText(orderDto.getState()));
		eventPublisher.publish(event);

		log.debug("order has been saved id:{}, event published{}", savedOrder.getId(), event.toString());

		return savedOrder;
	}

}
