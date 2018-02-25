package com.soaringclouds.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soaringclouds.product.converter.ProductConverter;
import com.soaringclouds.product.event.ProductEventProducer;
import com.soaringclouds.product.event.ToShoppingCartEventProducer;
import com.soaringclouds.product.model.CurrencyDO;
import com.soaringclouds.product.model.CurrencyEnum;
import com.soaringclouds.product.model.ProductDO;
import com.soaringclouds.product.repository.ProductRepository;

@Component
public class CurrencyServiceImpl implements CurrencyService {
	
	@Override
	public CurrencyDO getCurrency(String currency, String sessionId) {
		// @TODO implement currency handling => retrieve it from a cache of currencies
		return new CurrencyDO(CurrencyEnum.valueOf(currency), currency, 0.0d);
	}
		 
}
