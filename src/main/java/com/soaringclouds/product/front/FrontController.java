package com.soaringclouds.product.front;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
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
/*
    @GetMapping("/products")
	public String listProducts(Model model) {
		
		List<String> categories = new ArrayList<String>();
		categories.add("food");
		List<ProductDO> products = productService.findAll();
				
		
		model.addAttribute("products", products);
		
		return "products";
	}
*/
    private void setCustomerIdAttribute(String customerId) {
    		if (customerId == null && RequestContextHolder.currentRequestAttributes().getAttribute("customerId", RequestAttributes.SCOPE_SESSION) == null) {
    			throw new IllegalArgumentException("customerId not set initially!");
    		}
		if (customerId != null) {
			RequestContextHolder.currentRequestAttributes().setAttribute("customerId", customerId, RequestAttributes.SCOPE_SESSION);
		}
		
    }
    
    
    @GetMapping("/products")
	public String listProducts(@RequestParam(name="customerId", required=false) String customerId, Model model) {
    		setCustomerIdAttribute(customerId);
    		
		List<String> categories = new ArrayList<String>();
		categories.add("food");
		List<ProductDO> products = productService.findAll();
				
		
		model.addAttribute("products", products);
		
		return "products";
	}

    
	@GetMapping("/product/addToCart/{id}")
	public String addToCart(@PathVariable(value="id") String id, Model model) {
		String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
		String customerId = (String)RequestContextHolder.currentRequestAttributes().getAttribute("customerId", RequestAttributes.SCOPE_SESSION);

		if (customerId == null) {
			customerId = "not-logged-in";
		}

		ProductDO product = productService.findById(id);
		//CurrencyDO currency = currencyService.getCurrency("EUR");
		CurrencyDO currency = new CurrencyDO(CurrencyEnum.EUR, "EUR", 1.0);
		Integer quantity = 1;
		
		productService.addProductToShoppingCart(sessionId, customerId, currency, quantity, product);

		List<String> categories = new ArrayList<String>();
		categories.add("food");
		List<ProductDO> products = productService.findAll();
				
		
		model.addAttribute("products", products);
		
		return "products";
	}

	@GetMapping("/product/{id}")
	public String showProduct(@PathVariable(value="id") String id, Model model) {
		ProductDO product = productService.findById(id);
				
		model.addAttribute("product", product);
		
		return "productshow";
	}
	
	
	/*
	 * Product with search string
	 */
	@GetMapping("/productSearch")
	public String listProductsByNameSearch(@RequestParam("search-term") String searchTerm, Model model) {
		String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
		List<ProductDO> products = new ArrayList<>();
		if (searchTerm != null && searchTerm.length() > 0) {
			products = productService.findProductsBySearchString(searchTerm);
		} else {
			products = productService.findAll();
		}
		model.addAttribute("products", products);
		
		return "products";

	}
	
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