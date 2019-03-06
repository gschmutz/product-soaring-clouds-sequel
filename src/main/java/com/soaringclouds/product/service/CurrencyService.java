package com.soaringclouds.product.service;

import org.springframework.stereotype.Service;

import com.soaringclouds.product.model.CurrencyDO;

/*
 * Service Layer should be used for Transactional processes
 * 
 * Calls Repository Layers
 * 
 */
@Service
public interface CurrencyService {
    
    public CurrencyDO getCurrency(String currency, String sessionId);
}
