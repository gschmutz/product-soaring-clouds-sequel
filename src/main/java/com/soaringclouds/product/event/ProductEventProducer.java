package com.soaringclouds.product.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.soaringclouds.avro.v1.Product;

@Component
public class ProductEventProducer {
	@Autowired
	private KafkaTemplate<String, com.soaringclouds.avro.v1.Product> kafkaTemplate;
	
	@Value("${kafka.topic.product}")
	String kafkaTopic;
	
	public void produce(Product product) {
		kafkaTemplate.send(kafkaTopic, product.getProductId().toString(), product);
	}
}
