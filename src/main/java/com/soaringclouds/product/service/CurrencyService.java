package com.soaringclouds.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.soaringclouds.product.model.CurrencyDO;
import com.soaringclouds.product.model.ProductDO;

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
