package com.example.eshoptestapi.dto.Review;

import java.sql.Timestamp;

import java.sql.Timestamp;

public class ReviewDTO {

    private Integer rating;
    private String comment;
    private Timestamp createdAt;
    private Long productId;

    // Getters and Setters

    public Integer getRating() {
        return rating;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}