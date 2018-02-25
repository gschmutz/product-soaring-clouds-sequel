package com.soaringclouds.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soaringclouds.product.converter.ProductConverter;
import com.soaringclouds.product.event.ProductEventProducer;
import com.soaringclouds.product.event.ToShoppingCartEventProducer;
import com.soaringclouds.product.model.CurrencyDO;
import com.soaringclouds.product.model.ProductDO;
import com.soaringclouds.product.repository.ProductRepository;

@Component
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductEventProducer productEventProducer;
	
	@Autowired
	private ToShoppingCartEventProducer toShoppingCartEventProducer;

	@Override
	public void createProduct(ProductDO product) {
		//product.setId(UUID.randomUUID());
		productRepository.save(product);
		
		com.soaringclouds.avro.product.v1.Product avro = ProductConverter.convert(product);
		productEventProducer.produce(avro);
	}
	
	@Override
	public void modifyProduct(ProductDO product) {
		productRepository.save(product);

		com.soaringclouds.avro.product.v1.Product avro = ProductConverter.convert(product);
		productEventProducer.produce(avro);
	}	
	
	@Override
	public void removeProduct(ProductDO product) {
		//productRepository.remove(product);

		//com.soaringclouds.avro.product.v1.Product avro = ProductConverter.convert(product);
		//productEventProducer.produce(avro);
	}
	
	@Override
	public void addProductToShoppingCart(String sessionId, String customerId, CurrencyDO currency, int quantity, ProductDO product) {
		double priceInCurrency = product.getPrice() * currency.getExchangeRate();
		com.soaringclouds.avro.shoppingCartItem.v1.ShoppingCartItem avro = ProductConverter.convert(sessionId, customerId, currency, priceInCurrency, quantity, product);
		toShoppingCartEventProducer.produce(avro);
	}


	 
}
