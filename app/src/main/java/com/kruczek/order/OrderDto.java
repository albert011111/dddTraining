package com.kruczek.order;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class OrderDto {
	static OrderDto create(String id, String state, Set<ItemDto> items) {
		return new OrderDto(id, state, items);
	}

	private String id;
	private String state;
	private Set<ItemDto> items;

//	private OrderDto(long id, OrderState state, Set<ItemDto> items) {
//		this.id = id;
//		this.state = state;
//		this.items = items;
//	}

}
