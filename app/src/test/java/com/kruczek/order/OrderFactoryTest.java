package com.kruczek.order;

import java.util.Set;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class OrderFactoryTest {

	@ParameterizedTest
	@MethodSource("itemDtosData")
	void shouldCreateOrder_whenOrderDtoNotNull(Set<ItemDto> items) {
		//given
		final String id = "id";
		final String state = OrderState.COMPLETED.getText();

		final OrderDto dto = OrderDto.create(id, state, items);
		//when
		final Order order = OrderFactory.fromDto(dto);

		//then
		Assertions.assertThat(order).isNotNull();
	}

	@Test()
	void shouldThrowIllegalArgumentException_whenOrderDtoIsNull() {
		Assertions.assertThatThrownBy(() -> OrderFactory.fromDto(null))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("orderDto can't be null");
	}

	private static Stream<Set<ItemDto>> itemDtosData() {
		return Stream.of(
				Set.of(createItemDto()),
				null
		);
	}

	private static ItemDto createItemDto() {
		final long id = 1L;
		final String totalPrice = "5.55";
		final String currency = "PLN";
		//when
		return ItemDto.create(id, totalPrice, currency);
	}

}