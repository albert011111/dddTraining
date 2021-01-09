package com.kruczek.email;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
class EmailWarmup implements ApplicationListener<ContextRefreshedEvent> {
	private final EmailInitializer emailInitializer;

	EmailWarmup(final EmailRepository emailRepository, final EmailQueryRepository emailQueryRepository) {
		this.emailInitializer = new EmailInitializer(emailRepository, emailQueryRepository);
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		emailInitializer.init();
	}
}
