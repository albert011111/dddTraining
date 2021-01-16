package com.kruczek.order;

import java.util.Collection;

class OrderSnapshot {
	private String id;
	private OrderState state;
	private Collection<ItemSnapshot> items;

	public OrderSnapshot(String id, OrderState state, Collection<ItemSnapshot> items) {
		this.id = id;
		this.state = state;
		this.items = items;
	}

	public String getId() {
		return id;
	}

	public OrderState getState() {
		return state;
	}

	public Collection<ItemSnapshot> getItems() {
		return items;
	}
}
