package com.kruczek.order;


import java.util.Collections;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderTest {

	@Test
	void orderShouldChangeState() {
		//given
		final Order order = new Order("test1", OrderState.NEW, Collections.emptySet());
		//when
		final OrderEvent orderEvent = order.updateOrder(OrderState.CANCELLED);
		//then
		assertThat(orderEvent)
				.isNotNull()
				.extracting("orderId", "orderState")
				.containsExactly("test1", OrderState.CANCELLED);
	}

	@Test
	void exceptionShouldBeThrownWhenNewOrderStateIsNull() {
		//given
		final Order order = new Order("test1", OrderState.NEW, Collections.emptySet());
		//expected
		assertThatThrownBy(() -> order.updateOrder(null))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("new order state can't be null");
	}
}