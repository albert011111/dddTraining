package com.kruczek.mongodb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

//@Configuration
class MongoDbCfg  {


	@Bean
	MongoDatabaseFactory mongoDatabaseFactory() {
		return new SimpleMongoClientDatabaseFactory("test");
	}

	@Bean
	MongoTemplate mongoTemplate() {
		return new MongoTemplate(mongoDatabaseFactory());
	}

}
