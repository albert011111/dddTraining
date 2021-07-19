package com.kruczek.order;

import java.util.Objects;
import java.util.Set;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class OrderDto {
	private String id;
	private String state;
	private Set<ItemDto> items;

	static OrderDto create(String id, String state, Set<ItemDto> items) {
		Objects.requireNonNull(state, "state can't be null");
		return new OrderDto(id, state, items);
	}

}
