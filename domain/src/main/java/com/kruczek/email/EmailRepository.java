package com.kruczek.email;

import java.util.List;
import java.util.Optional;

interface EmailRepository {
	Email save(Email email);

	void delete(String emailId);

	Optional<Email> findById(String emailId);

	List<Email> findAllByFromAddress(String fromAddress);

}
