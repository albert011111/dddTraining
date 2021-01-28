package com.kruczek.email;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.kruczek.infrastructure.clients.FileGeneratorClient;

@EnableMongoRepositories(basePackageClasses = SqlQueryEmailRepository.class)
@Configuration
class EmailCfg {

	@Bean
	EmailFacade emailFacade(final EmailRepository emailRepository, final FileGeneratorClient fileGeneratorClient) {
		return new EmailFacade(emailRepository, fileGeneratorClient);
	}

}
