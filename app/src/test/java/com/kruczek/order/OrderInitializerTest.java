package com.kruczek.order;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ArgumentsSources;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderInitializerTest {

	@Mock
	OrderQueryRepository orderQueryRepository;
	@Mock
	OrderRepository orderRepository;

	@ParameterizedTest
	@MethodSource(value = "repoData")
	void givenElementsInRepo_shouldSaveOrder_whenRepositoryEmpty(int elementsInRepo, int numberOfInvocations) {
		//given
		final OrderInitializer orderInitializer = new OrderInitializer(orderRepository, orderQueryRepository);
		when(orderQueryRepository.count()).thenReturn(elementsInRepo);
		//when
		orderInitializer.init();
		//then
		verify(orderRepository, times(numberOfInvocations)).save(any(Order.class));
	}

	private static Stream<Arguments> repoData() {
		return Stream.of(
				Arguments.of(0, 1),
				Arguments.of(444, 0)
		);
	}

}