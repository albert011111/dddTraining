package com.kruczek.email;


class Email {
	private final String id;
	private final String from;
	private final String to;
	private final String text;

	static Email restore(EmailSnapshot snapshot) {
		return new Email(snapshot.getId(), snapshot.getFrom(), snapshot.getTo(), snapshot.getText());
	}

	EmailSnapshot getSnapshot() {
		return new EmailSnapshot(id, from, to, text);
	}

	private Email(String id, String from, String to, String text) {
		this.id = id;
		this.from = from;
		this.to = to;
		this.text = text;
	}


}
