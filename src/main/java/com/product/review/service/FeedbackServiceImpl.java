package com.product.review.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.product.review.dto.FeedbackDTO;
import com.product.review.entity.Feedback;
import com.product.review.exception.ProductNotFoundException;
import com.product.review.repository.FeedbackRepository;
import com.product.review.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.product.review.dto.ProductDTO;
import com.product.review.entity.Product;




@Service
public class FeedbackServiceImpl implements FeedbackService{
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Feedback addFeedback(FeedbackDTO feedbackDTO, int productId) {
        Optional<Product> optional = productRepository.findById(productId);
        if (optional.isPresent()) {
            Feedback feedback = new Feedback();
            feedback.setFeedback(feedbackDTO.getFeedback());
            feedback.setRating(feedbackDTO.getRating());
//            feedback.setAvgRating(feedbackDTO.getAvgRating());
            optional.get().getFeedbacks().add(feedback);

            return feedbackRepository.save(feedback);

        }
        throw new ProductNotFoundException("No Product found with given id to add feedback");
    }
//        Feedback feedback = new Feedback();
//        feedback.setFeedback(feedbackDTO.getFeedback());
//        feedback.setRating(feedbackDTO.getRating());
//        feedback.setAvgRating(feedbackDTO.getAvgRating());
//        feedback.setProducts(feedbackDTO.getProducts());

        //    public void rateProduct(int feedbackId, double rating) {
//        Optional<Feedback> optional = feedbackRepository.findById(feedbackId);
//        if(optional.isPresent()){
//            Feedback user = feedbackRepository.findById(feedbackId).get();
//            if(user.getRating() != 0) {
//            	user.setRating(rating);
//            }
//        }
//
//    }

//    @Override
//    public void giveFeedback(int feedbackId, FeedbackDTO feedbackDTO) {
//        Optional<Feedback> optional = feedbackRepository.findById(feedbackId);
//        if(optional.isPresent()){
//            Feedback user = feedbackRepository.findById(feedbackId).get();
//            if(user.getFeedback() != null) {
//            	user.setFeedback(feedbackDTO.getFeedback());
//            }
//            feedbackRepository.save(user);
//        }
//
//    }

    @Override
    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    @Override
    public Feedback getFeedbackById(int feedbackId) {
        Optional<Feedback> optional = feedbackRepository.findById(feedbackId);
        if(optional.isPresent()) {
            return feedbackRepository.findById(feedbackId).get();
        }
        else {
            throw new ProductNotFoundException("No product found with given id");
        }
    }

    public void updateDetails(int feedbackId, FeedbackDTO feedbackDTO , ProductDTO productDTO) {
        Optional<Feedback> optional = feedbackRepository.findById(feedbackId);
        if (optional.isPresent()) {
            Feedback user = feedbackRepository.findById(feedbackId).get();
            user.setRating(feedbackDTO.getRating());
            user.setFeedback(feedbackDTO.getFeedback());
//            user.setAvgRating(feedbackDTO.getAvgRating());
//            user.setProducts(feedbackDTO.getProducts());


            feedbackRepository.save(user);
        }
    }
    public void deleteFeedbackById(int feedbackId) {
        Optional<Feedback> optional = feedbackRepository.findById(feedbackId);
        if(optional.isPresent()){
            feedbackRepository.deleteById(feedbackId);
        }
    }
    public List<Feedback> findFeedbacksWithSorting(String field){
            return  feedbackRepository.findAll(Sort.by(Sort.Direction.ASC,field));
        }
        public Page<Feedback> findFeedbacksWithPagination(int offset,int pageSize){
            Page<Feedback> feedbacks = feedbackRepository.findAll(PageRequest.of(offset, pageSize));
            return  feedbacks;
        }
        public Page<Feedback> findFeedbacksWithPaginationAndSorting(int offset,int pageSize,String field){
            Page<Feedback> feedbacks = feedbackRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
            return  feedbacks;
        }
    }
