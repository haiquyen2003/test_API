package com.example.eshoptestapi.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.eshoptestapi.repository.ReviewRepository;
import com.example.eshoptestapi.entity.Review;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/reviews")

public class ReviewController {
    @Autowired
    private ReviewRepository reviewRepository;
    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id){
        Optional<Review> review = reviewRepository.findById(id);
        if(review.isPresent()){
            return ResponseEntity.ok(review.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping
    public List<Review> getAllReviews(){
        return reviewRepository.findAll();
    }
    @PostMapping
    public Review createReview(@RequestBody Review review){
        return reviewRepository.save(review);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id,@RequestBody Review reviewDetails){
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        if(reviewOptional.isPresent()){
            Review review = reviewOptional.get();
            review.setRating(reviewDetails.getRating());
            review.setComment(reviewDetails.getComment());
            review.setProduct(reviewDetails.getProduct());
            review.setId(reviewDetails.getId());
            return ResponseEntity.ok(reviewRepository.save(review));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id){
        if(reviewRepository.existsById(id)){
            reviewRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    


}
