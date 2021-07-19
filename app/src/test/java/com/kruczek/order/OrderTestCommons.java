package com.kruczek.order;

import java.util.Set;

class OrderTestCommons {
	private OrderTestCommons() {
	}

	static OrderDto createOrder(String id, OrderState orderState, Set<ItemDto> items) {
		return OrderDto.create(id, orderState.getText(), items);
	}
}
