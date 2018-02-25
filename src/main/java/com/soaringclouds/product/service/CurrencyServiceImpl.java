package com.soaringclouds.product.service;

import org.springframework.stereotype.Component;

import com.soaringclouds.product.model.CurrencyDO;
import com.soaringclouds.product.model.CurrencyEnum;

@Component
public class CurrencyServiceImpl implements CurrencyService {
	
	@Override
	public CurrencyDO getCurrency(String currency, String sessionId) {
		// @TODO implement currency handling => retrieve it from a cache of currencies
		return new CurrencyDO(CurrencyEnum.valueOf(currency), currency, 1d);
	}
		 
}
