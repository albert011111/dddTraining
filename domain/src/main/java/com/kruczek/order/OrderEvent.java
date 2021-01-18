package com.kruczek.order;

import java.time.Instant;

import com.kruczek.DomainEvent;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class OrderEvent implements DomainEvent {
	private final Instant occurredOn;
	private final String orderId;
	private final OrderState orderState;

	OrderEvent(Instant occurredOn, String orderId, OrderState orderState) {
		this.occurredOn = occurredOn;
		this.orderId = orderId;
		this.orderState = orderState;
	}

}
