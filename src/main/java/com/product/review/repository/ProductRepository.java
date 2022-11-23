package com.product.review.repository;

import com.product.review.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.review.entity.Product;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    public List<Product> findByCategory(String category);
//    public List<Feedback> findByFeedback(String feedback);
}
