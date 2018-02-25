package com.soaringclouds.product.api;

import com.soaringclouds.product.model.DimensionDO;
import com.soaringclouds.product.model.ProductDO;

public class ProductConverter {
	
	public static ProductApi convert (ProductDO product) {
		ProductApi value = new ProductApi();
		
		value.setProductId(product.getId());
		value.setProductCode(product.getProductCode());
		value.setProductName(product.getProductName());
		value.setImageUrl(product.getImageUrl());
		value.setPrice(product.getPrice());
		value.setSize(product.getSize());
		value.setWeight(product.getWeight());
		if (product.getDimension() != null) {
			value.setDimension(new DimensionApi(product.getDimension().getUnit(),
					product.getDimension().getLength(), product.getDimension().getHeight(), product.getDimension().getWidth()));
		}
		value.setColor(product.getColor());
		value.setTags(product.getTags());
		value.setCategories(product.getCategories());
		
		return value;
	}
	
	public static ProductDO convert (ProductApi product) {
		ProductDO value = new ProductDO();
		
		value.setId(product.getProductId());
		value.setProductCode(product.getProductCode());
		value.setProductName(product.getProductName());
		value.setImageUrl(product.getImageUrl());
		value.setPrice(product.getPrice());
		value.setSize(product.getSize());
		value.setWeight(product.getWeight());
		value.setCategories(product.getCategories());
		if (product.getDimension() != null) {
			value.setDimension(new DimensionDO(product.getDimension().getUnit(),
					product.getDimension().getLength(), product.getDimension().getHeight(), product.getDimension().getWidth()));
		}
		value.setColor(product.getColor());
		value.setTags(product.getTags());
		value.setCategories(product.getCategories());

		return value;
	}
	
}
