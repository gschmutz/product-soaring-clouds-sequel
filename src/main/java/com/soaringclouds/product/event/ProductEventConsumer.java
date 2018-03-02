package com.soaringclouds.product.event;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.soaringclouds.avro.product.v1.Product;

//@Component
public class ProductEventConsumer {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductEventConsumer.class);

	@KafkaListener(topics = "${kafka.topic.product}")
	public void receive(ConsumerRecord<String, Product> consumerRecord) {
		Product product = consumerRecord.value();
		// Product product = (Product) SpecificData.get().deepCopy(Product.SCHEMA$,
		// consumerRecord.value());
		LOGGER.info("received payload='{}'", product.toString());
	}
}
