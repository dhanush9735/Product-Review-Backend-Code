package com.product.review.dto;

import java.util.List;

import com.product.review.entity.Product;
import lombok.Builder;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
public class FeedbackDTO {
    private int feedbackId;
    @Max(value = 10, message = "Please provide value between 1-10")
    private double rating;
    @NotNull(message = "Feedback cant be null")
    @Size(min = 4, message = "Feedback should be of more than 3")
    private String feedback;
    private Product products;


    public Product getProducts() {
		return products;
	}

	public void setProducts(Product products) {
		this.products = products;
	}

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

//    public double getAvgRating() {
//        return avgRating;
//    }
//
//    public void setAvgRating(double avgRating) {
//        this.avgRating = avgRating;
//    }
}
