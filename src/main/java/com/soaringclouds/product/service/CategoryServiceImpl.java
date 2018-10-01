package com.soaringclouds.product.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CategoryServiceImpl implements CategoryService {
	
	//@Autowired
	//private CategoryRepository categoryRepository;
	
	@Override
	public List<String> findAll() {
		List<String> categories = new ArrayList<String>();
		categories.add("men");
		categories.add("woman");
		categories.add("boy");
		
		return categories;
	}
	


}
