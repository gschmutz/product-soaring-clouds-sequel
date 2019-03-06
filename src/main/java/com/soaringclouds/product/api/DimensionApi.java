package com.soaringclouds.product.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DimensionApi {
	
	
    @JsonProperty(value = "unit", required = false)
    private String unit;
    
    @JsonProperty(value = "length", required = false)
    private double length;

    @JsonProperty(value = "height", required = false)
    private double height;
    
    @JsonProperty(value = "width", required = false)
    private double width;
    
    public DimensionApi() {
    	
    }
    
    public DimensionApi(String unit, double length, double height, double width) {
    		this.unit = unit;
    		this.length = length;
    		this.height = height;
    		this.width = width;
    }

	public String getUnit() {
		return unit;
	}

	public double getLength() {
		return length;
	}

	public double getHeight() {
		return height;
	}

	public double getWidth() {
		return width;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public void setWidth(double width) {
		this.width = width;
	}
    
    
}
