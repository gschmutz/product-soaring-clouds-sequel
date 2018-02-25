package com.soaringclouds.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.soaringclouds.avro.product.v1.Dimension;
import com.soaringclouds.avro.product.v1.Product;
import com.soaringclouds.avro.shoppingCartItem.v1.CurrencyEnum;
import com.soaringclouds.avro.shoppingCartItem.v1.ShoppingCartItem;
import com.soaringclouds.product.model.CurrencyDO;
import com.soaringclouds.product.model.DimensionDO;
import com.soaringclouds.product.model.ProductDO;

public class ProductConverter {
	public static List<String> convertFromCS (List<CharSequence> input) {
		List<String> value = new ArrayList<String>();
		
		if (input != null) {
			for (CharSequence cs : input) {
				value.add(input.toString());
			}
		}
		return value;
	}
	
	public static List<CharSequence> convertFromString (List<String> input) {
		List<CharSequence> value = new ArrayList<CharSequence>();
		
		if (input != null) {
			for (String str : input) {
				value.add(str);
			}
		}
		return value;
	}
	
	public static Product convert (ProductDO product) {
		Product value = new Product();
		
		value.setProductId(product.getId());
		value.setProductCode(product.getProductCode());
		value.setProductName(product.getProductName());
		value.setImageUrl(product.getImageUrl());
		value.setPrice(product.getPrice());
		value.setSize(product.getSize());
		value.setWeight(product.getWeight());
		value.setCategories(convertFromString(product.getCategories()));
		value.setDimension(new Dimension(product.getDimension().getUnit(),
				product.getDimension().getLength(), product.getDimension().getHeight(), product.getDimension().getWidth()));
		return value;
	}
	
	public static ProductDO convert (Product product) {
		ProductDO value = new ProductDO();
		value.setId(product.getProductId().toString());
		value.setProductCode(product.getProductCode().toString());
		value.setProductName(product.getProductName().toString());
		value.setImageUrl(product.getImageUrl().toString());
		value.setPrice(product.getPrice());
		value.setSize(product.getSize());
		value.setWeight(product.getWeight());
		value.setCategories(convertFromCS(product.getCategories()));
		value.setDimension(new DimensionDO(product.getDimension().getUnit().toString(),
				product.getDimension().getLength(), product.getDimension().getHeight(), product.getDimension().getWidth()));
		return value;
	}
	
	/**
	 * Converts domain objects to the Avro ShoppingCartItem object
	 * @param product
	 * @param currency
	 * @param priceInCurrency
	 * @param quantity
	 * @return
	 */
	public static ShoppingCartItem convert (String sessionId, String userId, CurrencyDO currency, Double priceInCurrency, int quantity, ProductDO product) {
		ShoppingCartItem toShoppingCart = new ShoppingCartItem();
		
		toShoppingCart.setSessionId(sessionId);
		toShoppingCart.setUserId(userId);
		toShoppingCart.setCurrency(CurrencyEnum.valueOf(currency.getCurrency().name()));
		toShoppingCart.setPriceInCurrency(priceInCurrency);
		toShoppingCart.setQuantity(quantity);

		// set the product data
		toShoppingCart.getProduct().setProductId(product.getId());
		toShoppingCart.getProduct().setProductCode(product.getProductCode());
		toShoppingCart.getProduct().setProductName(product.getProductName());
		toShoppingCart.getProduct().setImageUrl(product.getImageUrl());
		toShoppingCart.getProduct().setPrice(product.getPrice());
		toShoppingCart.getProduct().setSize(product.getSize());
		toShoppingCart.getProduct().setWeight(product.getWeight());
		toShoppingCart.getProduct().setDimension(new com.soaringclouds.avro.shoppingCartItem.v1.Dimension(product.getDimension().getUnit(),
											product.getDimension().getLength(), product.getDimension().getHeight(), product.getDimension().getWidth()));
				
		return toShoppingCart;
	}
	
}
