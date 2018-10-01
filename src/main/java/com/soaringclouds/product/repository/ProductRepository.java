package com.soaringclouds.product.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.soaringclouds.product.model.ProductDO;


/*
 * Repository Layer is responsible for retrival of data
 */
@Repository
public interface ProductRepository extends MongoRepository<ProductDO, String> {
	
	  ProductDO findById(String id);
	
	  @Query("{ 'productCode' : ?0 }")
	  ProductDO findByProductCode(String productCode);
	  
	  /*
	   * Same functionality with the query below
	   * 
	   * db.products.find({ categories: { '$in':['children'] } })
	   * 
	   */
	  @Query("{ 'categories': { $elemMatch: { $eq: ?0 } } }")
	  List<ProductDO> findProductsByCategory(String categoryName);
	  
	  /*
	   * db.products.find( { 'productName': /mens/i } );
	   * @Query("{ 'productName': /?0/i }")
	   * 
	   */
	  @Query("{ 'productName':{$regex:?0,$options:'i'} }") 
	  List<ProductDO> findProductsByProductNameRegex(String searchString);

	  /**
	   * find all products by search string. you need the following index on MongoDB: db.products.createIndex({ "$**": "text" },{ name: "TextIndex" })
	   * @param searchString
	   * @return
	   */
	  @Query("{ $text: { $search: ?0  } }")
	  List<ProductDO> findProductsBySearchString(String searchString);

}
