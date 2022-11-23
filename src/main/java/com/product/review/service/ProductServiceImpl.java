package com.product.review.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.product.review.dto.FeedbackDTO;
import com.product.review.entity.Feedback;
import com.product.review.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.product.review.dto.ProductDTO;
import com.product.review.entity.Product;
import com.product.review.exception.ProductNotFoundException;
import com.product.review.repository.ProductRepository;


@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public Product addProduct(ProductDTO productDTO) {
        Product p = new Product();
        p.setProductName(productDTO.getProductName());
        p.setProductPrice(productDTO.getProductPrice());
        p.setCategory(productDTO.getCategory());
        p.setSeller(productDTO.getSeller());
        p.setProductUrl(productDTO.getProductUrl());


        return productRepository.save(p);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product editProduct(int productId, Product productDTO) {
        Optional<Product> optional = productRepository.findById(productId);
        if(optional.isPresent()) {
            Product product = productRepository.findById(productId).get();
            product.setProductName(productDTO.getProductName());
            product.setProductPrice(productDTO.getProductPrice());
            product.setCategory(productDTO.getCategory());
            product.setSeller(productDTO.getSeller());
            product.setProductUrl(productDTO.getProductUrl());

            return productRepository.save(product);
        }
        throw new ProductNotFoundException("No product found with given id " + productId);
    }

    @Override
    public void deleteProduct(int productId) {
        Optional<Product> op = productRepository.findById(productId);
        if(op.isPresent()) {
            productRepository.deleteById(productId);
        }
        else {
            throw new ProductNotFoundException("No product found to delete with id " + productId);
        }

    }

    @Override
    public List<Product> getByCategory(String category) {
        return productRepository.findByCategory(category);
    }

//    @Override
//    public void approveFeedback(int id) {
//        Optional<Product> optionalProduct = productRepository.findById(id);
//        if(optionalProduct.isPresent()){
//            Product p = productRepository.findById(id).get();
//            p.setApprove(true);
//
//            productRepository.save(p);
//        }
//
//    }

	@Override
	public Product getProductById(int id) {
		Optional<Product> optional = productRepository.findById(id);
		if(optional.isPresent()) {
			return productRepository.findById(id).get();
		}
		else {
			throw new ProductNotFoundException("No product found with given id");
		}
	}

    @Override
    public List<Feedback> fetchFeedbackByProductId(int productId, ProductDTO productDTO) {
        Optional<Product> optional = productRepository.findById(productId);
        if(optional.isPresent()) {
            return productDTO.getFeedbacks();
        }
        else {
            throw new ProductNotFoundException("No product found with id");
        }
    }
    public List<Feedback> getFeedbackOfProduct(int productId){
        Optional<Product> optional = productRepository.findById(productId);
        if(optional.isPresent()){
            return optional.get().getFeedbacks();
        }
        else {
            throw new ProductNotFoundException("No product found wiht given id");
        }
    }

    @Override
    public double calculateAvgRating(int productId) {
        Optional<Product> optional = productRepository.findById(productId);
        if(optional.isPresent()) {
            List<Feedback> list = optional.get().getFeedbacks();
            List<Double> list1 = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                list1.add(list.get(i).getRating());
            }
            return list1.stream().mapToDouble(e -> e).average().getAsDouble();
        } else
            return 0;
    }

    @Override
    public List<Product> compare(int id1, int id2) {
        Optional<Product> optional = productRepository.findById(id1);
        Optional<Product> optional1 = productRepository.findById(id2);
        List<Product> products = new ArrayList<>();
        if(optional.isPresent() && optional1.isPresent()) {
            if (optional.get().getCategory().equals(optional.get().getCategory())) {
                products.add(optional.get());
                products.add(optional1.get());
                return products;
            } else {
                throw new ProductNotFoundException("Product category does not match");
            }
        }
        else{
            throw new ProductNotFoundException("No product with the ids given");
        }
    }


    public List<Product> findProductsWithSorting(String field){
        return  productRepository.findAll(Sort.by(Sort.Direction.ASC,field));
    }
    public Page<Product> findProductsWithPagination(int offset,int pageSize){
        Page<Product> products = productRepository.findAll(PageRequest.of(offset, pageSize));
        return  products;
    }
    public Page<Product> findProductsWithPaginationAndSorting(int offset,int pageSize,String field){
        Page<Product> products = productRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return  products;
    }
}

	