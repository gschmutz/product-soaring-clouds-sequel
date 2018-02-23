package com.soaringclouds.product;

import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.junit.Test;

import com.soaringclouds.avro.v1.Dimension;
import com.soaringclouds.avro.v1.Product;

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

}
