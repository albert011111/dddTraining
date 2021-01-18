package com.kruczek.order;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.kruczek.event.DomainEventPublisherExecutor;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableMongoRepositories(basePackageClasses = SqlOrderRepository.class)
class OrderCfg {
	private final DomainEventPublisherExecutor publisherExecutor;

	@Bean
	OrderFacade orderFacade(final OrderRepository orderRepository) {
		return new OrderFacade(orderRepository, publisherExecutor);
	}

}
