package com.product.review.service;

import java.util.List;

import com.product.review.dto.FeedbackDTO;
import com.product.review.entity.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.product.review.dto.ProductDTO;
import com.product.review.entity.Product;

@Service
public interface ProductService {
    public Product addProduct(ProductDTO productDTO);//post mapping

    public List<Product> getAllProducts();//get

    public Product editProduct(int productId, Product productDTO);//put

    public void deleteProduct(int productId);//delete

    public List<Product> getByCategory(String category);//get

    public Product getProductById(int id);
    public List<Feedback> fetchFeedbackByProductId(int productId, ProductDTO productDTO);

    public List<Feedback> getFeedbackOfProduct(int productId);
    public double calculateAvgRating(int productId);
    public List<Product> compare(int id1, int id2);

    public List<Product> findProductsWithSorting(String field);
    public Page<Product> findProductsWithPagination(int offset,int pageSize);
    public Page<Product> findProductsWithPaginationAndSorting(int offset, int pageSize, String field);
}
