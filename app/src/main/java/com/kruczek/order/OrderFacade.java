package com.kruczek.order;

import java.util.Set;
import java.util.stream.Collectors;

import org.javamoney.moneta.Money;

class OrderFacade {
	private final OrderRepository orderRepository;

	OrderFacade(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public OrderDto saveOrder(OrderDto orderDto) {
		return toOrderDto(orderRepository.save(OrderFactory.fromDto(orderDto)));
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
