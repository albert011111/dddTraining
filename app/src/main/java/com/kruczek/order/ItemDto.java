package com.kruczek.order;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class ItemDto {
	static ItemDto create(final long id, final String totalPrice, final String currency) {
		return new ItemDto(id, totalPrice, currency);
	}

	private long id;
	private String totalPrice;
	private String currency;
}
