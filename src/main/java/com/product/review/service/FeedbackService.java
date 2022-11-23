package com.product.review.service;

import com.product.review.dto.FeedbackDTO;
import com.product.review.dto.ProductDTO;
import com.product.review.entity.Feedback;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FeedbackService {
    public Feedback addFeedback(FeedbackDTO feedbackDTO, int productId);
    public List<Feedback> getAllFeedback();
    public void updateDetails(int feedbackId, FeedbackDTO feedbackDTO , ProductDTO productDTO);

    public Feedback getFeedbackById(int feedbackId);
    public void deleteFeedbackById(int feedbackId);

    public List<Feedback> findFeedbacksWithSorting(String field);
    public Page<Feedback> findFeedbacksWithPagination(int offset, int pageSize);
    public Page<Feedback> findFeedbacksWithPaginationAndSorting(int offset,int pageSize,String field);





}
