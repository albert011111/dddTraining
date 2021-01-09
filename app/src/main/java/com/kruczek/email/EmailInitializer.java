package com.kruczek.email;

import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

class EmailInitializer {
	private final EmailRepository emailRepository;
	private final EmailQueryRepository emailQueryRepository;

	EmailInitializer(EmailRepository emailRepository, EmailQueryRepository emailQueryRepository) {
		this.emailRepository = emailRepository;
		this.emailQueryRepository = emailQueryRepository;
	}

	void init() {
		if (emailQueryRepository.count() == 0) {
			emailRepository.save(Email.restore(new EmailSnapshot(UUID.randomUUID().toString(),
					"papryk215nk@gmail.com",
					"patryk.kruczek@gmail.com",
					"This is my vety first mail that I have sent!")));
		}
	}
}
