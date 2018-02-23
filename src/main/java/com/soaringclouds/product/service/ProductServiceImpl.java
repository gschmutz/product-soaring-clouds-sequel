package com.soaringclouds.product.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.soaringclouds.product.converter.ProductConverter;
import com.soaringclouds.product.event.ProductEventProducer;
import com.soaringclouds.product.model.ProductDO;
import com.soaringclouds.product.repository.ProductRepository;

@Component
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductEventProducer productEventProducer;

	@Override
	public void createProduct(ProductDO product) {
		//product.setId(UUID.randomUUID());
		productRepository.save(product);
		
		com.soaringclouds.avro.v1.Product avro = ProductConverter.convert(product);
		productEventProducer.produce(avro);
	}
	
}
