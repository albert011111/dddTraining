package com.kruczek.order;

import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kruczek.DomainEventPublisher;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderFacadeTest {

	@Mock
	OrderRepository repository;
	@Mock
	DomainEventPublisher domainEventPublisher;

	@Test
	void shouldSaveOrder_whenDataProvided() {
		//given
		OrderFacade facade = new OrderFacade(repository, domainEventPublisher);
		OrderDto dto = OrderTestCommons.createOrder("testId", OrderState.CANCELLED, Set.of());

		when(repository.save(any())).thenReturn(new Order("testId", OrderState.NEW, Set.of()));
		doNothing().when(domainEventPublisher).publish(any());

		//when
		final OrderDto resultDto = facade.saveOrder(dto);
		//then
		assertThat(resultDto).isNotNull();
		verify(repository, times(1)).save(any());
		verify(domainEventPublisher, times(1)).publish(any());
	}
}