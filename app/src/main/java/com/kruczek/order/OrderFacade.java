package com.kruczek.order;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import org.javamoney.moneta.Money;

import com.kruczek.DomainEventPublisher;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder
class OrderFacade {
	private final OrderRepository orderRepository;
	private final DomainEventPublisher eventPublisher;

	public OrderFacade(OrderRepository orderRepository, DomainEventPublisher eventPublisher) {
		this.orderRepository = orderRepository;
		this.eventPublisher = eventPublisher;
	}

	public OrderDto saveOrder(OrderDto orderDto) {
		final OrderDto savedOrder = toOrderDto(orderRepository.save(OrderFactory.fromDto(orderDto)));
		final OrderEvent event = new OrderEvent(Instant.now(), savedOrder.getId(), OrderState.fromText(orderDto.getState()));
		eventPublisher.publish(event);

		log.debug("order has been saved id:{}, event published{}", savedOrder.getId(), event.toString());

		return savedOrder;
	}

	private OrderDto toOrderDto(Order order) {
		final var snapshot = order.getSnapshot();
		return OrderDto.create(snapshot.getId(), snapshot.getState().getText(), toItemDtos(snapshot));
	}

	private Set<ItemDto> toItemDtos(OrderSnapshot snapshot) {
		return snapshot.getItems().stream()
				.map(this::toItemDto)
				.collect(Collectors.toSet());
	}

	private ItemDto toItemDto(ItemSnapshot snapshot) {
		final Money price = snapshot.getTotalPrice();
		return ItemDto.create(snapshot.getId(),
				price.getNumberStripped().toString(),
				price.getCurrency().toString());
	}

}
