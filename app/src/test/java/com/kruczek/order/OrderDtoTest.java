package com.kruczek.order;

import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderDtoTest {

	@Test
	void shouldCreateOrderDto_whenArgumentsProvided() {
		//given
		final String id = UUID.randomUUID().toString();
		//when
		final OrderDto orderDto = OrderTestCommons.createOrder(id, OrderState.NEW, Set.of());
		//then
		assertThat(orderDto)
				.isNotNull()
				.satisfies(dto -> {
					assertThat(dto.getId()).isEqualTo(id);
					assertThat(dto.getState()).isEqualTo("new");
					assertThat(dto.getItems()).isNotNull().isEmpty();
				});
	}

}