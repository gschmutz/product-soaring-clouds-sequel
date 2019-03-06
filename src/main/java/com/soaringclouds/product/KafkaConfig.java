package com.soaringclouds.product;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import com.soaringclouds.avro.product.v1.Product;
import com.soaringclouds.avro.shoppingCartItem.v1.ShoppingCartItem;

import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;

@Configuration
public class KafkaConfig {
	  @Value("${spring.kafka.bootstrap-servers}")
	  private String bootstrapServers;

	  @Value("${spring.kafka.schema-registry-url}")
	  private String schemaRegistryURL;	  
	  
	  @Bean
	  public Map<String, Object> producerConfigs() {
	    Map<String, Object> props = new HashMap<>();
	    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
	    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
	    props.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryURL);

	    return props;
	  }
	  
	  @Bean
	  public Map<String, Object> consumerConfigs() {
	    Map<String, Object> props = new HashMap<>();
	    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
	    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
	    props.put(ConsumerConfig.GROUP_ID_CONFIG, "product");
	    props.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryURL);
	    props.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryURL);
	    props.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);

	    return props;
	  }
	  
	  @Bean
	  public ProducerFactory<String, Product> producerFactory() {
	    return new DefaultKafkaProducerFactory<>(producerConfigs());
	  }

	  @Bean
	  public ProducerFactory<String, ShoppingCartItem> producerFactoryShoppingCartItem() {
	    return new DefaultKafkaProducerFactory<>(producerConfigs());
	  }

	  @Bean
	  public KafkaTemplate<String, Product> kafkaTemplate() {
	    return new KafkaTemplate<>(producerFactory());
	  }

	  @Bean
	  public KafkaTemplate<String, ShoppingCartItem> kafkaTemplateShoppingCartItem() {
	    return new KafkaTemplate<>(producerFactoryShoppingCartItem());
	  }
	  
	  @Bean
	  public ConsumerFactory<String, Product> consumerFactory() {
	    return new DefaultKafkaConsumerFactory<>(consumerConfigs());
	  }
	  
	  @Bean
	  public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Product>> kafkaListenerContainerFactory() {
	    ConcurrentKafkaListenerContainerFactory<String, Product> factory =
	        new ConcurrentKafkaListenerContainerFactory<>();
	    factory.setConsumerFactory(consumerFactory());

	    return factory;
	  }
}
