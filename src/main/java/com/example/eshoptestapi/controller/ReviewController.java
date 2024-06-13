package com.example.eshoptestapi.controller;

import com.example.eshoptestapi.dto.Review.ReviewDTO;
import com.example.eshoptestapi.entity.Product;
import com.example.eshoptestapi.entity.Review;
import com.example.eshoptestapi.entity.User;
import com.example.eshoptestapi.service.ProductService;
import com.example.eshoptestapi.service.ReviewService;
import com.example.eshoptestapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        Optional<Review> review = reviewService.getReviewById(id);
        return review.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping
    public ResponseEntity<Review> addReview(@RequestBody ReviewDTO reviewDTO, Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Optional<Product> productOpt = productService.getProductById(reviewDTO.getProductId());
        if (!productOpt.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Product product = productOpt.get();

        // Tạo đối tượng Review mới
        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        // Thiết lập thông tin đánh giá
        review.setRating(reviewDTO.getRating());
        review.setComment(reviewDTO.getComment());
        review.setCreatedAt(reviewDTO.getCreatedAt());

        // Lưu đối tượng Review vào cơ sở dữ liệu
        Review newReview = reviewService.addReview(review);
        return new ResponseEntity<>(newReview, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody ReviewDTO reviewDTO, Authentication authentication) {
        Optional<Review> existingReview = reviewService.getReviewById(id);
        if (!existingReview.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Review review = existingReview.get();
        // Đảm bảo rằng chỉ người dùng đã tạo đánh giá mới có thể cập nhật nó
        if (!review.getUser().getId().equals(user.getId())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        // Cập nhật thông tin đánh giá mà không thay đổi sản phẩm
        review.setRating(reviewDTO.getRating());
        review.setComment(reviewDTO.getComment());
        review.setCreatedAt(reviewDTO.getCreatedAt());

        Review updatedReview = reviewService.updateReview(review);
        return new ResponseEntity<>(updatedReview, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        if (!reviewService.getReviewById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        reviewService.deleteReview(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
