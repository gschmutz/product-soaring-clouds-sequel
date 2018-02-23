package com.soaringclouds.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.soaringclouds.product.model.ProductDO;

/*
 * Service Layer should be used for Transactional processes
 * 
 * Calls Repository Layers
 * 
 */
@Service
public interface ProductService {
    
    //public List<ProductDO> findAll();
    //public ProductDO findByProductCode(String productCode);
    //public List<ProductDO> findProductsByCategory(String categoryName);
    //public List<ProductDO> findProductsByName(String searchString);
    public void createProduct(ProductDO product);
    
}
