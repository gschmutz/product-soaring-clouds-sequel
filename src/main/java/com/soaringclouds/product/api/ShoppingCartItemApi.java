package com.soaringclouds.product.api;

import java.math.BigInteger;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.soaringclouds.product.model.DimensionDO;

public class ShoppingCartItemApi {

    @JsonProperty(value = "sessionId", required = true)
    public String sessionId;
    
    @JsonProperty(value = "userId", required = true)
    public String userId;

    @JsonProperty(value = "currency", required = true)
    public String currency;
    
    @JsonProperty(value = "quantity", required = true)
    public Integer quantity;

    @JsonProperty(value = "product", required = true)
    public ProductApi product;
    
}
