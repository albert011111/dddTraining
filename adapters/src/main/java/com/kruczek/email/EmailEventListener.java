package com.kruczek.email;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.kruczek.order.OrderEvent;

@Service
class EmailEventListener {
	private final EmailFacade emailFacade;

	EmailEventListener(EmailFacade emailFacade) {
		this.emailFacade = emailFacade;
	}

	@EventListener
	public void onEvent(OrderEvent event){
		emailFacade.handle(event);
	}
}
