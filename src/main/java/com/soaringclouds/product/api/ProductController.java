package com.soaringclouds.product.api;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import javax.activation.FileTypeMap;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsCriteria;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.base.Preconditions;
import com.mongodb.gridfs.GridFSDBFile;
import com.soaringclouds.product.model.CurrencyDO;
import com.soaringclouds.product.model.ProductDO;
import com.soaringclouds.product.repository.ProductRepository;
import com.soaringclouds.product.service.CurrencyService;
import com.soaringclouds.product.service.ProductService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private ProductService productService;
    
	@Autowired
	private GridFsOperations gridOperations;    

    private void createProduct(ProductApi productApi) throws ParseException {
        ProductDO productDO = ProductConverter.convert(productApi);
        productService.createProduct(productDO);
        LOGGER.info("Prodcut created: " + productDO);
    }
    
    private void modifyProduct(ProductApi productApi) throws ParseException {
        ProductDO productDO = ProductConverter.convert(productApi);
        productService.modifyProduct(productDO);
        LOGGER.info("Prodcut created: " + productDO);
    }

    private void createToShoppingCart(ShoppingCartItemApi toShoppingCartApi) throws ParseException {
        ProductDO productDO = ProductConverter.convert(toShoppingCartApi.product);
        CurrencyDO currencyDO = currencyService.getCurrency(toShoppingCartApi.currency, toShoppingCartApi.sessionId);
        productService.addProductToShoppingCart(toShoppingCartApi.sessionId, toShoppingCartApi.customerId, currencyDO, toShoppingCartApi.quantity, productDO);
        LOGGER.info("Prodcut created: " + productDO);
    }
    
    private List<GridFSDBFile> getFiles() {
        return gridOperations.find(null);
    }

    private Optional<GridFSDBFile> maybeLoadFile(String name) {
        GridFSDBFile file = gridOperations.findOne(getFilenameQuery(name));
        return Optional.ofNullable(file);
    }

    private static Query getFilenameQuery(String name) {
        return Query.query(GridFsCriteria.whereFilename().is(name));
    }

    @RequestMapping(value= "/products",
            method = RequestMethod.POST,
            consumes = "application/json") 
    @Transactional
    public void postProduct(@RequestBody @Valid ProductApi productApi) throws ParseException {
        Preconditions.checkNotNull(productApi);
        
        createProduct(productApi);
    }
    
    @RequestMapping(value= "/product",
            method = RequestMethod.PUT,
            consumes = "application/json") 
    @Transactional
    public void putProduct(@RequestBody @Valid ProductApi productApi) throws ParseException {
        Preconditions.checkNotNull(productApi);
        Preconditions.checkNotNull(productApi.getProductId());
        
        modifyProduct(productApi);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value= "/product/{id}"
    )
    //@CrossOrigin(origins = "http://localhost:4200")
    public ProductApi getProduct(@PathVariable(value="id") String id)  {
        ProductApi product = new ProductApi();
        ProductDO productDO = null;
        // trim leading and training double quote
        id = StringUtils.trimTrailingCharacter(StringUtils.trimLeadingCharacter(id, '"'),'"');
        
        //productsDO = productRepository.findAll(); 
        
        if (id != null && id.length() > 0) {
        		productDO = productRepository.findById(id);
        }
        
        if(productDO != null) {
        		product = ProductConverter.convert(productDO);
        }
        return product;
    }
    
    @RequestMapping(
            method = RequestMethod.GET,
            value= "/products"
    )
    //@CrossOrigin(origins = "http://localhost:4200")
    public List<ProductApi> getProducts(@RequestParam(value="code", defaultValue="") String code,
    										@RequestParam(value="name", defaultValue="") String name,
    										@RequestParam(value="categoryName", defaultValue="") String categoryName)  {
        ProductApi product = new ProductApi();
        List<ProductDO> productsDO = new ArrayList<ProductDO>();
        List<ProductApi> products = new ArrayList<ProductApi>();
        Predicate<ProductDO> pred = null;
        
        // trim leading and training double quote
//        id = StringUtils.trimTrailingCharacter(StringUtils.trimLeadingCharacter(id, '"'),'"');
        code = StringUtils.trimTrailingCharacter(StringUtils.trimLeadingCharacter(code, '"'),'"');
        name = StringUtils.trimTrailingCharacter(StringUtils.trimLeadingCharacter(name, '"'),'"');
        categoryName = StringUtils.trimTrailingCharacter(StringUtils.trimLeadingCharacter(categoryName, '"'),'"');
        
        //productsDO = productRepository.findAll(); 
        
        if (name != null && name.length() > 0) {		
            	productsDO = productRepository.findProductsByProductNameRegex(name);        	
        } else if (categoryName != null && categoryName.length() > 0) {
        		productsDO = productRepository.findProductsByCategory(categoryName);        	
        } else if (code != null && code.length() > 0) {	
        	System.out.println (code);        	
    			ProductDO productDO = productRepository.findByProductCode(code);  
    			if (productDO != null) {
    				productsDO.add(productDO);
    			}
        		//productsDO.removeIf(p-> !(p.getProductCode().equals(code)));
        } else {
        		productsDO = productRepository.findAll();   
        }
        
        if (pred != null)
        		productsDO.removeIf(pred);
        
        for (ProductDO productDO : productsDO) {
        		product = ProductConverter.convert(productDO);
        		products.add(product);
        }
        return products;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value= "/images/{id}"
    )
    public ResponseEntity<byte[]> getImage(@PathVariable(value="id") String id) throws IOException{
        
    		String fileName = id + ".jpg";
        GridFSDBFile imageFile = gridOperations.findOne(new Query(Criteria.where("filename").is(fileName)));
        imageFile.writeTo("/tmp/"+imageFile.getFilename());
        File img = new File("/tmp/"+imageFile.getFilename());
        return ResponseEntity.ok().contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img))).body(Files.readAllBytes(img.toPath()));
    }   
    
    @RequestMapping(
    			method = RequestMethod.POST,
    			value="/images")
    public HttpEntity<byte[]> createOrUpdate(@RequestParam("file") MultipartFile file) {
      String name = file.getOriginalFilename();
      try {
        Optional<GridFSDBFile> existing = maybeLoadFile(name);
        if (existing.isPresent()) {
        	gridOperations.delete(getFilenameQuery(name));
        }
        gridOperations.store(file.getInputStream(), name, file.getContentType()).save();
        String resp = "<script>window.location = '/';</script>";
        return new HttpEntity<>(resp.getBytes());
      } catch (IOException e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }    
    
    @RequestMapping(value= "/shoppingCart",
            method = RequestMethod.POST,
            consumes = "application/json") 
    @Transactional
    public void postShoppingCart(@RequestBody @Valid ShoppingCartItemApi toShoppingCartApi) throws ParseException {
        Preconditions.checkNotNull(toShoppingCartApi);
        Preconditions.checkNotNull(toShoppingCartApi.product, "the product object should not be NULL");
        
        createToShoppingCart(toShoppingCartApi);
    }
    
}