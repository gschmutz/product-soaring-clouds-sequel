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

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository sut;

    @Test
    public void shouldFindProduct() {
        ProductDO product = sut.findByProductCode("AX329T");

        Assert.assertEquals("AX329T", product.getProductCode());
    }

    @Test
    public void shouldSaveProduct() {
        ProductDO product = new ProductDO();
        product.setColor("red");
        product.setDimension(new DimensionDO("cm", 10.2d, 4.3d, 3.0d));
        product.setProductCode("XZ344T");
        product.setPrice(23.30);
        product.setProductName("Productname");
        product.setWeight(4.5);
        
        sut.save(product);
    }
}
