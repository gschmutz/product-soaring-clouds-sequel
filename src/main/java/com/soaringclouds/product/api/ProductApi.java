package com.soaringclouds.product.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductApi {

    @JsonProperty(value = "productId", required = true)
    public String productId;
    
    @JsonProperty(value = "code", required = true)
    public String productCode;
    
    @JsonProperty(value = "name", required = true)
    public String productName; 
    
    @JsonProperty(value = "description", required = true)
    public String description;

    @JsonProperty(value = "imageUrl", required = false)
    private String imageUrl;
    
    @JsonProperty(value = "price", required = false)
    private double price;
    
    @JsonProperty(value = "size", required = false)
    private int size;
    
    @JsonProperty(value = "weight", required = false)
    private double weight;
    
    @JsonProperty(value = "dimension", required = false)
    private DimensionApi dimension;
    
    @JsonProperty(value = "color", required = false)
    private String color;

    @JsonProperty(value = "tags", required = false)
    private List<String> tags;

    @JsonProperty(value = "categories", required = false)
    private List<String> categories;

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public DimensionApi getDimension() {
		return dimension;
	}

	public void setDimension(DimensionApi dimension) {
		this.dimension = dimension;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    
    
}
