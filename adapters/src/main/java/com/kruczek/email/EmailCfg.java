package com.kruczek.email;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = SqlQueryEmailRepository.class)
@Configuration
class EmailCfg {

	@Bean
	EmailFacade emailFacade(final EmailRepository emailRepository) {
		return new EmailFacade(emailRepository);
	}

}
