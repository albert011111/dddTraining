package com.kruczek.order;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum OrderState {
	NEW("new"),
	IN_PROGRESS("inProgress"),
	COMPLETED("completed"),
	CANCELLED("cancelled");

	private final String text;

	OrderState(String text) {
		this.text = text;
	}

	String getText() {
		return text;
	}

	static OrderState fromText(String txt) {
		return Arrays
				.stream(OrderState.values())
				.filter(order -> order.getText().equals(txt))
				.findFirst()
				.orElseThrow(() -> new NoSuchElementException("no enum for provided string value"));
	}
}
