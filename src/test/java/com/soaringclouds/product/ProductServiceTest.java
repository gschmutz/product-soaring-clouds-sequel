package com.soaringclouds.product;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.soaringclouds.product.model.DimensionDO;
import com.soaringclouds.product.model.ProductDO;
import com.soaringclouds.product.repository.ProductRepository;
import com.soaringclouds.product.service.ProductService;
import com.soaringclouds.product.service.ProductServiceImpl;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceTest {

    @Autowired
    private ProductService sut;

    @Test
    public void shouldSaveProduct() throws InterruptedException {
        ProductDO product = new ProductDO();
        product.setColor("red");
        product.setDimension(new DimensionDO("cm", 10.2d, 4.3d, 3.0d));
        product.setProductCode("XZ344T");
        product.setPrice(23.30);
        product.setProductName("Productname");
        product.setWeight(4.5);
        
        sut.createProduct(product);
        
        Thread.sleep(10000);
    }
}
