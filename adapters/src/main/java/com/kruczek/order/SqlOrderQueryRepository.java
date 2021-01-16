package com.kruczek.order;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

interface SqlQueryOrderRepository extends MongoRepository<OrderSnapshot, String> {


}

interface SqlOrderRepository extends MongoRepository<OrderSnapshot, String> {
	OrderSnapshot save(OrderSnapshot snapshot);
}

@Repository
class SqlOrderQueryRepositoryImpl implements OrderRepository {
	private final SqlOrderRepository sqlOrderRepository;

	SqlOrderQueryRepositoryImpl(SqlOrderRepository sqlOrderRepository) {
		this.sqlOrderRepository = sqlOrderRepository;
	}

	@Override
	public Order save(Order toSave) {
		return Order.restore(sqlOrderRepository.save(toSave.getSnapshot()));
	}
}
