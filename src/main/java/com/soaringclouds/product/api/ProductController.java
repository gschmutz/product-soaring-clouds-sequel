package com.soaringclouds.product.api;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Preconditions;
import com.soaringclouds.product.model.ProductDO;
import com.soaringclouds.product.repository.ProductRepository;
import com.soaringclouds.product.service.ProductService;

@RestController
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    private void saveProduct(ProductApi productApi) throws ParseException {
        ProductDO productDO = ProductConverter.convert(productApi);
        productService.createProduct(productDO);
        LOGGER.info("Prodcut created: " + productDO);
    }
    
    @RequestMapping(value= "/product",
            method = RequestMethod.POST,
            consumes = "application/json") 
    @Transactional
    public void postProduct(@RequestBody @Valid ProductApi productApi) throws ParseException {
        Preconditions.checkNotNull(productApi);
        
        saveProduct(productApi);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = "application/json",
            value = "/products"
    )
    @Transactional
    public void postProducts(@RequestBody @Valid ProductApi[] productApis) throws ParseException {
        Preconditions.checkNotNull(productApis);
        for (ProductApi productApi : productApis) {
            saveProduct(productApi);
        }
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value= "/products"
    )
    public List<ProductApi> getProducts(@RequestParam(value="name", defaultValue="") String name,
    										@RequestParam(value="categoryName", defaultValue="") String categoryName)  {
        ProductApi product = new ProductApi();
        List<ProductDO> productsDO = null;
        List<ProductApi> products = new ArrayList<ProductApi>();
        
        if (name != null & name.length() > 0) {
            	productsDO = productRepository.findProductsByProductNameRegex(name);        	
        } else if (categoryName != null & categoryName.length() > 0) {
        		productsDO = productRepository.findProductsByCategory(categoryName);        	
        } else {
        		productsDO = productRepository.findAll();   
        }
        
        for (ProductDO productDO : productsDO) {
        		product = ProductConverter.convert(productDO);
        		products.add(product);
        }
        return products;
    }

    @RequestMapping(
            method = RequestMethod.GET, 
            value = "/product"
    )
    public ProductApi getProduct(@RequestParam(value="code", defaultValue="") String code)  {
        ProductApi product = new ProductApi();
        ProductDO productDO = null;
        
        if (code != null && code.length() > 0) {
        		productDO = productRepository.findByProductCode(code);
        } 
        System.out.println(productDO);
        product = ProductConverter.convert(productDO);
        return product;
    }

}