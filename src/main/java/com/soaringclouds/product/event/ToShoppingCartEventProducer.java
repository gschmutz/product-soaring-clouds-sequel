package com.soaringclouds.product.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.soaringclouds.avro.shoppingCartItem.v1.ShoppingCartItem;

@Component
public class ToShoppingCartEventProducer {
	@Autowired
	private KafkaTemplate<String, ShoppingCartItem> kafkaTemplate;
	
	@Value("${kafka.topic.addToShoppingCart}")
	String kafkaTopic;
	
	public void produce(ShoppingCartItem shoppingCartItem) {
		kafkaTemplate.send(kafkaTopic, shoppingCartItem.getSessionId().toString(), shoppingCartItem);
	}
}
