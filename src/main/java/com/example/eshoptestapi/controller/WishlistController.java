package com.example.eshoptestapi.controller;

import com.example.eshoptestapi.entity.WishList;
import com.example.eshoptestapi.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/wishlist")

public class WishlistController {
    @Autowired
    private WishlistRepository wishlistRepository;

    @GetMapping
    public List<WishList> getAllWishList() {
        return wishlistRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WishList> getWishListById(@PathVariable Long id) {
        Optional<WishList> wishList = wishlistRepository.findById(id);
        if (wishList.isPresent()) {
            return ResponseEntity.ok(wishList.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public WishList createWishList(@RequestBody WishList wishList) {
        return wishlistRepository.save(wishList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WishList> updateWishList(@PathVariable Long id, @RequestBody WishList wishList) {
        Optional<WishList> existingWishList = wishlistRepository.findById(id);
        if (existingWishList.isPresent()) {
            WishList updatedWishList = existingWishList.get();
            updatedWishList.setProduct(wishList.getProduct());
            updatedWishList.setId(wishList.getId());
            updatedWishList.setUser(wishList.getUser());
            updatedWishList.setCreatedAt(wishList.getCreatedAt());
            return ResponseEntity.ok(wishlistRepository.save(updatedWishList));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<WishList> deleteWishList(@PathVariable Long id) {
        Optional<WishList> wishList = wishlistRepository.findById(id);
        if (wishList.isPresent()) {
            wishlistRepository.delete(wishList.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
