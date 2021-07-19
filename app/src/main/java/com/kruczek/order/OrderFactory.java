package com.kruczek.order;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.javamoney.moneta.Money;

class OrderFactory {
	static Order fromDto(OrderDto orderDto) {
		return Optional
				.ofNullable(orderDto)
				.map(OrderFactory::toOrder)
				.orElseThrow(() -> new IllegalArgumentException("orderDto can't be null"));
	}

	static OrderDto toOrderDto(Order order) {
		final var snapshot = order.getSnapshot();
		return OrderDto.create(snapshot.getId(), snapshot.getState().getText(), toItemDtos(snapshot));
	}

	private static Order toOrder(OrderDto dto) {
		return Order.restore(new OrderSnapshot(dto.getId(), OrderState.fromText(dto.getState()), toItemSnapshots(dto)));
	}

	private static Set<ItemSnapshot> toItemSnapshots(OrderDto dto) {
		if (dto.getItems() == null) {
			return Collections.emptySet();
		}
		return dto.getItems().stream().map(OrderFactory::toSnapshot).collect(Collectors.toSet());
	}

	private static ItemSnapshot toSnapshot(ItemDto itemDto) {
		return new ItemSnapshot(itemDto.getId(), Money.of(new BigDecimal(itemDto.getTotalPrice()), itemDto.getCurrency()));
	}

	private static Set<ItemDto> toItemDtos(OrderSnapshot snapshot) {
		return snapshot.getItems().stream()
				.map(OrderFactory::toItemDto)
				.collect(Collectors.toSet());
	}

	private static ItemDto toItemDto(ItemSnapshot snapshot) {
		final Money price = snapshot.getTotalPrice();
		return ItemDto.create(snapshot.getId(),
				price.getNumberStripped().toString(),
				price.getCurrency().toString());
	}


	private OrderFactory() {
	}
}
