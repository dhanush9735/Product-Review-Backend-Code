package com.product.review.controller;

import java.util.List;

import com.product.review.dto.FeedbackDTO;
import com.product.review.entity.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.product.review.dto.ProductDTO;
import com.product.review.entity.Product;
import com.product.review.service.ProductService;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = {"http://localhost:9090", "http://localhost:4200"},allowedHeaders = "*")

public class ProductController {
	@Autowired
	private ProductService productService;
	
	@PostMapping("/addproduct")
	public ResponseEntity<String> addsProduct(@Valid @RequestBody ProductDTO productDTO){
		productService.addProduct(productDTO);
		return new ResponseEntity<>("Product is added",HttpStatus.OK);
	}
	
	@GetMapping("/allproducts")
	public List<Product> allProducts(){
		return productService.getAllProducts();
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<String> editsProduct(@PathVariable int id ,@RequestBody Product productDTO){
		productService.editProduct(id, productDTO);
		return new ResponseEntity<>("Product is updated",HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable int id){
		productService.deleteProduct(id);
		return new ResponseEntity<>("Product is deleted ",HttpStatus.OK);
	}
	
	@GetMapping("/product/{category}")
	public List<Product> byCategory(@PathVariable String category){
		return productService.getByCategory(category);
		
	}
   @GetMapping("/getproduct/{productId}")
   public Product getAProductById(@PathVariable int productId) {
	   return productService.getProductById(productId);
   }

   @GetMapping("/feedback/{productId}")
	public List<Feedback> fetchByProductId(@PathVariable int productId) {
		return productService.getFeedbackOfProduct(productId);
   }
	@GetMapping("/avgrating/{productId}")
	public Double fetchAvg(@PathVariable int productId){
		return productService.calculateAvgRating(productId);
	}
	@GetMapping("/compare/{id1}/{id2}")
	public List<Product> compareProducts(@PathVariable int id1, @PathVariable int id2){
		return productService.compare(id1,id2);
	}

	@GetMapping("/sort/{field}")
	public List<Product> findProductsWithSorting(@PathVariable String field) {
		return productService.findProductsWithSorting(field);
	}
	@GetMapping("/pagination/{offset}/{pageSize}")
	public Page<Product> findProductsWithPagination(@PathVariable int offset,@PathVariable int pageSize) {
		return productService.findProductsWithPagination(offset, pageSize);
	}
	@GetMapping("/page/{offset}/{pageSize}/{field}")
	public Page<Product> findProductsWithPaginationAndSorting(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) {
		return productService.findProductsWithPaginationAndSorting(offset, pageSize, field);
	}

}
