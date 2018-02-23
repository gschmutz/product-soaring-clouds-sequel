package com.soaringclouds.product.model;

public class DimensionDO {
    private String unit;
    private double length;
    private double height;
    private double width;
    
    public DimensionDO(String unit, double length, double height, double width) {
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
    
    
}
