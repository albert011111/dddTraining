package com.kruczek.email;

import java.util.Objects;

import com.kruczek.email.dto.EmailDto;

public class EmailFacade {
	private final EmailRepository emailRepository;

	public EmailFacade(EmailRepository emailRepository) {
		this.emailRepository = emailRepository;
	}

	public EmailDto saveEmail(EmailDto emailDto) {
		Objects.requireNonNull(emailDto, "emailDto can't be null!");

		final EmailSnapshot emailSnapshot = emailRepository
				.save(Email.restore(EmailFactory.snapshotFrom(emailDto)))
				.getSnapshot();

		return EmailFactory.dtoFrom(emailSnapshot);
	}

}
