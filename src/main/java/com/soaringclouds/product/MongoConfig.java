package com.soaringclouds.product;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.kafka.core.KafkaTemplate;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.soaringclouds.avro.product.v1.Product;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {
	
	@Value("${spring.data.mongodb.address}")
	private String mongoAddress; 
	
	@Value("${spring.data.mongodb.database}")
	private String mongoDatabase;
	
	@Bean
	public GridFsTemplate gridFsTemplate() throws Exception {
	    return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
	}
	
	@Override
	protected String getDatabaseName() {
		return mongoDatabase;
	}
 
	@Override
	public Mongo mongo() throws Exception {
		return new MongoClient(mongoAddress);
	}
}
