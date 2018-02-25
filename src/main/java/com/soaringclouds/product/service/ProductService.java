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
public interface ProductService {
    
    public void createProduct(ProductDO product);
    public void modifyProduct(ProductDO product);
    public void removeProduct(ProductDO product);
    
	public void addProductToShoppingCart(String sessionId, String customerId, CurrencyDO currency, int quantity, ProductDO product);
}
