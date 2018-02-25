package com.soaringclouds.product.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShoppingCartItemApi {

    @JsonProperty(value = "sessionId", required = true)
    public String sessionId;
    
    @JsonProperty(value = "customerId", required = true)
    public String customerId;

    @JsonProperty(value = "currency", required = true)
    public String currency;
    
    @JsonProperty(value = "quantity", required = true)
    public Integer quantity;

    @JsonProperty(value = "product", required = true)
    public ProductApi product;
    
}
