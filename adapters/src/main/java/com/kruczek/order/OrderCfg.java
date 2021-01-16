package com.kruczek.order;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = SqlOrderRepository.class)
@Configuration
class OrderCfg {

	@Bean
	OrderFacade orderFacade(final OrderRepository orderRepository) {
		return new OrderFacade(orderRepository);
	}

}
