package com.kruczek.order;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.javamoney.moneta.Money;

class Order {
	static Order restore(OrderSnapshot snapshot) {
		return new Order(snapshot.getId(), snapshot.getState(), snapshot.getItems()
				.stream()
				.map(Item::restore)
				.collect(Collectors.toSet()));
	}

	private final String id;
	private final OrderState state;
	private final Set<Item> items = new HashSet<>();

	Order(String id, OrderState state, Set<Item> items) {
		this.id = id;
		this.state = state;
		changeItems(items);
	}

	private void changeItems(Set<Item> items) {
		this.items.clear();
		this.items.addAll(items);
	}

	OrderSnapshot getSnapshot() {
		return new OrderSnapshot(id, state, toItemSnapshots());
	}

	private List<ItemSnapshot> toItemSnapshots() {
		return items.stream().map(Item::getSnapshot).collect(Collectors.toList());
	}

	static class Item {
		static Item restore(ItemSnapshot itemSnapshot) {
			return new Item(itemSnapshot.getId(), itemSnapshot.getTotalPrice());
		}

		private final long id;
		private final Money totalPrice;

		Item(long id, Money totalPrice) {
			this.id = id;
			this.totalPrice = totalPrice;
		}

		ItemSnapshot getSnapshot() {
			return new ItemSnapshot(this.id, this.totalPrice);
		}
	}
}
