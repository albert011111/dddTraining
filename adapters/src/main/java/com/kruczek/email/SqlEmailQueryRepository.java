package com.kruczek.email;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

interface SqlQueryEmailRepository extends EmailQueryRepository, MongoRepository<EmailSnapshot, String> {
}

interface SqlEmailRepository extends MongoRepository<EmailSnapshot, String> {
	EmailSnapshot save(EmailSnapshot snapshot);
}

@Repository
class EmailRepositoryImpl implements EmailRepository {
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

	}

	@Override
	public Optional<Email> findById(String emailId) {
		return Optional.empty();
	}

	@Override
	public List<Email> findAllByFromAddress(String fromAddress) {
		return null;
	}
}
