package com.kruczek.order;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ItemDtoTest {

	@Test
	void shouldCreateItemDto_whenArgumentsProvided() {
		//given
		final long id = 1L;
		final String totalPrice = "5.55";
		final String currency = "PLN";
		//when
		final ItemDto itemDto = ItemDto.create(id, totalPrice, currency);
		//then
		assertThat(itemDto)
				.isNotNull()
				.satisfies(dto -> {
					assertThat(dto.getId()).isEqualTo(id);
					assertThat(dto.getTotalPrice()).isEqualTo("5.55");
					assertThat(dto.getCurrency()).isEqualTo("PLN");
				});
	}
}