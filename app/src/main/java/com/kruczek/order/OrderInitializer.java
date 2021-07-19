package com.kruczek.order;

import java.util.Collections;
import java.util.UUID;

class OrderInitializer {
	private final OrderRepository orderRepository;
	private final OrderQueryRepository orderQueryRepository;

	OrderInitializer(OrderRepository orderRepository, OrderQueryRepository orderQueryRepository) {
		this.orderRepository = orderRepository;
		this.orderQueryRepository = orderQueryRepository;
	}

	void init() {
		if (orderQueryRepository.count() == 0) {
			Order order = Order.restore(new OrderSnapshot(UUID.randomUUID().toString(), OrderState.NEW, Collections.emptySet()));
			orderRepository.save(order);
		}
	}
}
