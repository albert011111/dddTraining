package com.kruczek.email;

import java.util.Objects;
import java.util.Optional;

import com.kruczek.FeignClient;
import com.kruczek.email.dto.EmailDto;
import com.kruczek.order.OrderEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmailFacade {
	private final EmailRepository emailRepository;
	private final FeignClient fileGeneratorClient;

	public EmailFacade(EmailRepository emailRepository, FeignClient fileGeneratorClient) {
		this.emailRepository = Objects.requireNonNull(emailRepository, "emailRepository can't be null");
		this.fileGeneratorClient = Objects.requireNonNull(fileGeneratorClient,
				"fileGeneratorClient can't be null");
	}

	public EmailDto saveEmail(EmailDto emailDto) {
		Objects.requireNonNull(emailDto, "emailDto can't be null!");

		final EmailSnapshot emailSnapshot = emailRepository
				.save(Email.restore(EmailFactory.snapshotFrom(emailDto)))
				.getSnapshot();

		return EmailFactory.dtoFrom(emailSnapshot);
	}

	//todo replace cases with 'real' implementation
	public void handle(OrderEvent event) {
		Optional.ofNullable(event)
				.ifPresent(orderEvent -> {

					switch (orderEvent.getOrderState()) {
						case NEW:
							saveEmail(EmailDto.builder()
									.text("Time: [" + orderEvent.getOccurredOn()
											+ "] New order has been created! Order id: " + orderEvent.getOrderId())
									.to("patryk.kruczek@gmail.com")
									.from("app.manager@gmail.com")
									.build());

							final String call = fileGeneratorClient.call();

							log.debug("received from API call message: {}",call);

							break;
						case IN_PROGRESS:
						case COMPLETED:
							saveEmail(EmailDto.builder()
									.text("Time: [" + orderEvent.getOccurredOn()
											+ "] Order has changed state! Order id: " + orderEvent.getOrderId()
											+ "state: " + orderEvent.getOrderState())
									.to("patryk.kruczek@gmail.com")
									.from("app.manager@gmail.com")
									.build());
							break;
						case CANCELLED:
							saveEmail(EmailDto.builder()
									.text("Time: [" + orderEvent.getOccurredOn()
											+ "] Order has been cancelled! Order id: " + orderEvent.getOrderId())
									.to("patryk.kruczek@gmail.com")
									.from("app.manager@gmail.com")
									.build());
							break;
						default:
							break;
					}

				});
	}
}
