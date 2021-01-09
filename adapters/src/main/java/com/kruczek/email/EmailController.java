package com.kruczek.email;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kruczek.email.dto.EmailDto;

@RequestMapping(value = "/v1/emails")
@RestController
class EmailController {
	private final EmailFacade emailFacade;
	private final SqlQueryEmailRepository sqlQueryEmailRepository;

	EmailController(EmailRepository emailRepository, SqlQueryEmailRepository sqlQueryEmailRepository) {
		this.emailFacade = new EmailFacade(emailRepository);
		this.sqlQueryEmailRepository = sqlQueryEmailRepository;
	}

	//todo Add request body validation. Add excepttion handling
	@PostMapping(value = "email")
	ResponseEntity<EmailDto> sendEmail(@RequestBody EmailDto emailBody) {
		final EmailDto emailDto = emailFacade.saveEmail(emailBody);

		return ResponseEntity
				.created(URI.create("/" + emailDto.getId()))
				.body(emailDto);
	}

	@GetMapping
	List<EmailDto> getEmails() {
		return new ArrayList<>(sqlQueryEmailRepository.findBy(EmailDto.class));
	}
}
