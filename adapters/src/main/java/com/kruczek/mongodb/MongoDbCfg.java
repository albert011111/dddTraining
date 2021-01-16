package com.kruczek.mongodb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

@Configuration
class MongoDbCfg {

	@Value("${spring.data.mongodb.uri}")
	private String mongoDbURI;

//	@Bean
//	MappingMongoConverter mongoConverter(MongoDatabaseFactory mongoDatabaseFactory, MongoMappingContext mongoMappingContext) {
//		DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDatabaseFactory);
//		MappingMongoConverter mongoConverter = new MappingMongoConverter(dbRefResolver, mongoMappingContext);
//		mongoConverter.setMapKeyDotReplacement("#");
//		return mongoConverter;
//	}

	@Bean
	MongoTemplate mongoTemplate() {
		final MongoTemplate mongoTemplate = new MongoTemplate(mongoDatabaseFactory());
		((MappingMongoConverter)mongoTemplate.getConverter()).setMapKeyDotReplacement("#dot#");

		return mongoTemplate;
	}

	@Bean
	MongoDatabaseFactory mongoDatabaseFactory() {
		return new SimpleMongoClientDatabaseFactory(mongoDbURI);
	}

}
