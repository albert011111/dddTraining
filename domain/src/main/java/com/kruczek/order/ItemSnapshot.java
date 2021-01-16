package com.kruczek.order;

import org.javamoney.moneta.Money;

final class ItemSnapshot {

	private long id;
	private Money totalPrice;

	ItemSnapshot(long id, Money totalPrice) {
		this.id = id;
		this.totalPrice = totalPrice;
	}

	long getId() {
		return id;
	}

	Money getTotalPrice() {
		return totalPrice;
	}
}
