package com.example.eshoptestapi.service;

import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.eshoptestapi.entity.Review;
import com.example.eshoptestapi.repository.ReviewRepository;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public Review getReviewbyID(Long id) {
        return reviewRepository.getReviewbyID(id);
    }

    public List<Review> findByProductId(Long productId) {
        return reviewRepository.findByProductId(productId);
    }
    
    public List<Review> findByUserId(Long userId) {
        return reviewRepository.findByUserId(userId);
    }

    public List<Review> findByProductIdAndUserId(Long productId, Long userId) {
        return reviewRepository.findByProductIdAndUserId(productId, userId);
    }

    public List<Review> findByComment(String comment) {
        return reviewRepository.findByComment(comment);
    }

    
}
