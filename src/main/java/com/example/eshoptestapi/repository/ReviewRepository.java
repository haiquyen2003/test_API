package com.example.eshoptestapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.eshoptestapi.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{
        @Query(value = "SELECT r FROM Review r WHERE r.id = :id")
        Review getReviewbyID(Long id);
        @Query(value = "SELECT r FROM Review r WHERE r.product.id = :productId")
        List<Review> findByProductId(Long productId);
        @Query(value = "SELECT r FROM Review r WHERE r.user.id = :userId")
        List<Review> findByUserId(Long userId);
        @Query(value = "SELECT r FROM Review r WHERE r.product.id = :productId AND r.user.id = :userId")
        List<Review> findByProductIdAndUserId(Long productId, Long userId);
        @Query(value = "SELECT r FROM Review r WHERE r.comment = :comment")
        List<Review> findByComment(String comment);

}
