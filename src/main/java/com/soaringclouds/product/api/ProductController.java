package com.soaringclouds.product.api;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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
import com.soaringclouds.product.model.CurrencyDO;
import com.soaringclouds.product.model.ProductDO;
import com.soaringclouds.product.repository.ProductRepository;
import com.soaringclouds.product.service.CurrencyService;
import com.soaringclouds.product.service.ProductService;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private ProductService productService;

    private void createProduct(ProductApi productApi) throws ParseException {
        ProductDO productDO = ProductConverter.convert(productApi);
        productService.createProduct(productDO);
        LOGGER.info("Prodcut created: " + productDO);
    }
    
    private void modifyProduct(ProductApi productApi) throws ParseException {
        ProductDO productDO = ProductConverter.convert(productApi);
        productService.modifyProduct(productDO);
        LOGGER.info("Prodcut created: " + productDO);
    }

    private void createToShoppingCart(ShoppingCartItemApi toShoppingCartApi) throws ParseException {
        ProductDO productDO = ProductConverter.convert(toShoppingCartApi.product);
        CurrencyDO currencyDO = currencyService.getCurrency(toShoppingCartApi.currency, toShoppingCartApi.sessionId);
        productService.addProductToShoppingCart(toShoppingCartApi.sessionId, toShoppingCartApi.customerId, currencyDO, toShoppingCartApi.quantity, productDO);
        LOGGER.info("Prodcut created: " + productDO);
    }

    @RequestMapping(value= "/product",
            method = RequestMethod.POST,
            consumes = "application/json") 
    @Transactional
    public void postProduct(@RequestBody @Valid ProductApi productApi) throws ParseException {
        Preconditions.checkNotNull(productApi);
        
        createProduct(productApi);
    }
    
    @RequestMapping(value= "/product",
            method = RequestMethod.PUT,
            consumes = "application/json") 
    @Transactional
    public void putProduct(@RequestBody @Valid ProductApi productApi) throws ParseException {
        Preconditions.checkNotNull(productApi);
        Preconditions.checkNotNull(productApi.getProductId());
        
        modifyProduct(productApi);
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
            createProduct(productApi);
        }
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value= "/products"
    )
    //@CrossOrigin(origins = "http://localhost:4200")
    public List<ProductApi> getProducts(@RequestParam(value="code", defaultValue="") String code,
    										@RequestParam(value="name", defaultValue="") String name,
    										@RequestParam(value="categoryName", defaultValue="") String categoryName)  {
        ProductApi product = new ProductApi();
        List<ProductDO> productsDO = new ArrayList<ProductDO>();
        List<ProductApi> products = new ArrayList<ProductApi>();
        
        if (name != null & name.length() > 0) {
            	productsDO = productRepository.findProductsByProductNameRegex(name);        	
        } else if (categoryName != null & categoryName.length() > 0) {
        		productsDO = productRepository.findProductsByCategory(categoryName);        	
        } else         if (code != null && code.length() > 0) {
    			productsDO.add( productRepository.findByProductCode(code));
        } else {
        		productsDO = productRepository.findAll();   
        }
        
        for (ProductDO productDO : productsDO) {
        		product = ProductConverter.convert(productDO);
        		products.add(product);
        }
        return products;
    }

    @RequestMapping(value= "/shoppingCart",
            method = RequestMethod.POST,
            consumes = "application/json") 
    @Transactional
    public void postShoppingCart(@RequestBody @Valid ShoppingCartItemApi toShoppingCartApi) throws ParseException {
        Preconditions.checkNotNull(toShoppingCartApi);
        Preconditions.checkNotNull(toShoppingCartApi.product, "the product object should not be NULL");
        
        createToShoppingCart(toShoppingCartApi);
    }

}