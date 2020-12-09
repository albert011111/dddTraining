package com.kruczek.email;

class EmailSnapshot {
	private String id;
	private String from;
	private String to;
	private String text;

	public EmailSnapshot(String id, String from, String to, String text) {
		this.id = id;
		this.from = from;
		this.to = to;
		this.text = text;
	}

	public String getId() {
		return id;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public String getText() {
		return text;
	}

}
