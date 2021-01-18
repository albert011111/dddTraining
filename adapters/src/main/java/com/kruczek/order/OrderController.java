package com.kruczek.order;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kruczek.DomainEventPublisher;

//todo add in headers version of API
@RequestMapping(value = "/v1/orders")
@RestController
class OrderController {
	private final OrderFacade orderFacade;

	OrderController(OrderRepository orderRepository, DomainEventPublisher domainEvent) {
		this.orderFacade = new OrderFacade(orderRepository, domainEvent);
	}

	//todo Add request body validation. Add excepttion handling
	@PostMapping(value = "/order")
	ResponseEntity<OrderDto> saveOrder(@RequestBody OrderDto orderBody) {
		final OrderDto orderDto = orderFacade.saveOrder(orderBody);

		return ResponseEntity
				.created(URI.create("/" + orderDto.getId()))
				.body(orderDto);
	}
}
