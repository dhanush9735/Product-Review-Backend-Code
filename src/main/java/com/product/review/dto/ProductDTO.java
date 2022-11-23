package com.product.review.dto;

import com.product.review.entity.Feedback;
import com.product.review.entity.Seller;
import lombok.Builder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
@Builder
public class ProductDTO {
    private int productId;
    @NotNull(message = "Name cant be null")
    @Size(min = 4, message = "Size should be more than 3")
    private String productName;
    private double productPrice;
    @NotNull(message = "Category cant be null")
    @Size(min = 4, message = "category should be more than 3")

    private String category;
    private Seller seller;
    private List<Feedback> feedbacks;
    private String productUrl;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }
    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String imageUrl) {
        this.productUrl = productUrl;
    }
}
