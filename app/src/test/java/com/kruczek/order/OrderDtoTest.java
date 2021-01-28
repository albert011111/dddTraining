package com.kruczek.order;

import java.util.Set;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderDtoTest {

	@Test
	void shouldCreateOrderDto_whenArgumentsProvided() {
		//given
		final String id = UUID.randomUUID().toString();
		final String state = OrderState.NEW.getText();
		final Set<ItemDto> items = Set.of();
		//when
		final OrderDto orderDto = OrderDto.create(id, state, items);
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