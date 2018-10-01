package com.soaringclouds.product;

import java.util.ArrayList;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.junit.Test;

import com.soaringclouds.avro.product.v1.Dimension;
import com.soaringclouds.avro.product.v1.Product;
import com.soaringclouds.avro.shoppingCartItem.v1.CurrencyEnum;
import com.soaringclouds.avro.shoppingCartItem.v1.ShoppingCartItem;

public class TestKafkaAvro {

	private Producer<String, Product> connect() {
		Producer<String, Product> producer = null;

		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
		props.put("schema.registry.url", "http://localhost:8081");

		try {
			producer = new KafkaProducer<String, Product>(props);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return producer;
	}
	
	private Producer<String, ShoppingCartItem> connectShoppingCartItem() {
		Producer<String, ShoppingCartItem> producer = null;

		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
		props.put("schema.registry.url", "http://localhost:8081");

		try {
			producer = new KafkaProducer<String, ShoppingCartItem>(props);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return producer;
	}

	@Test
	public void test() {
		Product product = new Product();
		product.setProductId(UUID.randomUUID().toString());
		product.setDimension(new Dimension());
		Producer producer = connect();

		ProducerRecord<String, Product> record = new ProducerRecord<String, Product>("product", null, product);
		if (producer != null) {
			try {
				Future<RecordMetadata> future = producer.send(record);
				RecordMetadata metadata = future.get();
			} catch (Exception e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}

		}

	}
	@Test
	public void testShoppingCartItem() {
		ShoppingCartItem sci = new ShoppingCartItem();
		sci.setSessionId("sdfafsdffsdf");
		sci.setCustomerId("gschmutz");
		sci.setCurrency(CurrencyEnum.EUR);
		sci.setPriceInCurrency(1.0);
		sci.setQuantity(1);
		
		com.soaringclouds.avro.shoppingCartItem.v1.Product product = new com.soaringclouds.avro.shoppingCartItem.v1.Product();
		product.setProductId(UUID.randomUUID().toString());
		product.setProductCode("product code");
		product.setProductName("product name");
		product.setImageUrl("image url");
		product.setPrice(0.0d);
		product.setSize(42);
		product.setWeight(23.4d);
		product.setCategories(new ArrayList<>());
		product.setTags(new ArrayList<>());
		product.setColor("yellow");
		product.setDimension(new com.soaringclouds.avro.shoppingCartItem.v1.Dimension("cm", 1.0, 1.0,1.0));
		
		sci.setProduct(product);

		Producer<String, ShoppingCartItem> producer = connectShoppingCartItem();

		ProducerRecord<String, ShoppingCartItem> record = new ProducerRecord<String, ShoppingCartItem>("a516817-soaring-add-to-shopping-cart", null, sci);
		if (producer != null) {
			try {
				Future<RecordMetadata> future = producer.send(record);
				RecordMetadata metadata = future.get();
			} catch (Exception e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}

		}

	}
}
