package com.soaringclouds.product.front;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.RequestContextHolder;

import com.soaringclouds.product.model.CurrencyDO;
import com.soaringclouds.product.model.CurrencyEnum;
import com.soaringclouds.product.model.ProductDO;
import com.soaringclouds.product.service.CurrencyService;
import com.soaringclouds.product.service.ProductService;

@Controller
public class FrontController {
	
    @Autowired
    private ProductService productService;
	
    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/products")
	public String listProducts(Model model) {
		
		List<String> categories = new ArrayList<String>();
		categories.add("food");
		List<ProductDO> products = productService.findAll();
				
		
		model.addAttribute("products", products);
		
		return "products";
	}

	@GetMapping("/product/addToCart/{id}")
	public String addToCart(@PathVariable(value="id") String id, Model model) {
		String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
		ProductDO product = productService.findById(id);
		//CurrencyDO currency = currencyService.getCurrency("EUR");
		CurrencyDO currency = new CurrencyDO(CurrencyEnum.EUR, "EUR", 1.0);
		String customerId = "1";
		Integer quantity = 1;
		
		productService.addProductToShoppingCart(sessionId, customerId, currency, quantity, product);

		List<String> categories = new ArrayList<String>();
		categories.add("food");
		List<ProductDO> products = productService.findAll();
				
		
		model.addAttribute("products", products);
		
		return "products";
	}

	
//	
//	/*
//	 * Product with search string
//	 */
//	@RequestMapping(value = "/products", params="srch-term")
//	public ModelAndView listProductsByNameSearch(@RequestParam("srch-term") String searchTerm) {
////		List<Product> products = productService.findProductsByName(searchTerm);
////		List<String> categories = categoryService.findAll();
//		List<String> categories = new ArrayList<String>();
//		categories.add("food");
//		List<Product> products = new ArrayList<Product>();
//		
//		ModelAndView model = new ModelAndView("products");
//		
//		model.addObject("categoryList", categories);
//		model.addObject("productList", products);
//		
//		return model;
//	}
//	
//	@RequestMapping(value = "/products-by-category-{categoryName}")
//	public ModelAndView listProductsByCategory(@PathVariable("categoryName") String categoryName) {
////		List<Product> products = productService.findProductsByCategory(categoryName);
////		List<String> categories = categoryService.findAll();
//		List<String> categories = new ArrayList<String>();
//		categories.add("food");
//		List<Product> products = new ArrayList<Product>();
//		
//		ModelAndView model = new ModelAndView("products");
//		
//		model.addObject("categoryList", categories);
//		model.addObject("productList", products);
//		
//		return model;
//	}
//	

	
}