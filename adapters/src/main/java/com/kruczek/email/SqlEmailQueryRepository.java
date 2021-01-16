package com.kruczek.email;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unchecked")
interface SqlEmailRepository extends MongoRepository<EmailSnapshot, String> {
	EmailSnapshot save(EmailSnapshot snapshot);
}

interface SqlQueryEmailRepository extends EmailQueryRepository, MongoRepository<EmailSnapshot, String> {
}

@Repository
class EmailRepositoryImpl implements EmailRepository {
	private static final String NOT_IMPLEMENTED_YET = "not implemented yet :(";

	private final SqlQueryEmailRepository sqlQueryEmailRepository;
	private final SqlEmailRepository sqlEmailRepository;

	EmailRepositoryImpl(SqlQueryEmailRepository sqlQueryEmailRepository, SqlEmailRepository sqlEmailRepository) {
		this.sqlQueryEmailRepository = sqlQueryEmailRepository;
		this.sqlEmailRepository = sqlEmailRepository;
	}

	@Override
	public Email save(Email email) {
		return Email.restore(sqlEmailRepository.save(email.getSnapshot()));
	}

	@Override
	public void delete(String emailId) {
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
	}

	@Override
	public Optional<Email> findById(String emailId) {
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
	}

	@Override
	public List<Email> findAllByFromAddress(String fromAddress) {
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
	}
}
