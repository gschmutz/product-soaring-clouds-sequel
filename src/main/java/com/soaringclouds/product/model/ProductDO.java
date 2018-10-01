package com.soaringclouds.product.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="products")
public class ProductDO {
    
    @Id private String id;
    private String productCode;
    private String productName;
    private String description;
    private String imageUrl;
    private double price;
    private int size;
    private double weight;
    private DimensionDO dimension;
    private String color;
    private List<String> tags;
    private List<String> categories;
    
	public ProductDO() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
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

	public DimensionDO getDimension() {
		return dimension;
	}

	public void setDimension(DimensionDO dimension) {
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

	@Override
	public String toString() {
		return "ProductDO [id=" + id + ", productCode=" + productCode + ", productName=" + productName
				+ ", description=" + description + ", imageUrl=" + imageUrl + ", price=" + price + ", size=" + size
				+ ", weight=" + weight + ", dimension=" + dimension + ", color=" + color + ", tags=" + tags
				+ ", categories=" + categories + "]";
	}

}
