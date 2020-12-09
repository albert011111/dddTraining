package com.kruczek.email;

import javax.naming.OperationNotSupportedException;

import com.kruczek.email.dto.EmailDto;

final class EmailFactory {
	private EmailFactory() {
	}

	static Email from(EmailDto emailDto) throws OperationNotSupportedException {
		throw new OperationNotSupportedException("Not implemented yet");
	}

	static EmailSnapshot snapshotFrom(EmailDto emailDto) {
		return new EmailSnapshot(
				emailDto.getId(),
				emailDto.getFrom(),
				emailDto.getTo(),
				emailDto.getText());
	}

	static EmailDto dtoFrom(EmailSnapshot snapshot) {
		return EmailDto.builder()
				.id(snapshot.getId())
				.from(snapshot.getFrom())
				.to(snapshot.getTo())
				.text(snapshot.getText())
				.build();

	}
}
