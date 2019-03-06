package com.soaringclouds.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

/*
 * Service Layer should be used for Transactional processes
 * 
 * Calls Repository Layers
 * 
 */
@Service
public interface CategoryService {
	
	public List<String> findAll();
	
}
