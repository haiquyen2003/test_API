package com.example.eshoptestapi.controller;

import com.example.eshoptestapi.dto.WishList.WishListDTO;
import com.example.eshoptestapi.entity.User;
import com.example.eshoptestapi.service.UserService;
import com.example.eshoptestapi.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/wishlists")
public class WishListController {

    @Autowired
    private WishListService wishListService;

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping
    public ResponseEntity<List<WishListDTO>> getAllWishLists(Authentication authentication) {
        Long userId = userService.findByEmail(authentication.getName()).getId();
        List<WishListDTO> wishLists = wishListService.getAllWishListsForUser(userId);
        return new ResponseEntity<>(wishLists, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/{id}")
    public ResponseEntity<WishListDTO> getWishListById(@PathVariable Long id, Authentication authentication) {
        Long userId = userService.findByEmail(authentication.getName()).getId();
        Optional<WishListDTO> wishList = wishListService.getWishListById(id);
        if (wishList.isPresent() && wishList.get().getUserId().equals(userId)) {
            return new ResponseEntity<>(wishList.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping
    public ResponseEntity<WishListDTO> addWishList(@RequestBody WishListDTO wishListDTO, Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
        wishListDTO.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        WishListDTO newWishList = wishListService.addWishList(wishListDTO, user);
        return new ResponseEntity<>(newWishList, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PutMapping("/{id}")
    public ResponseEntity<WishListDTO> updateWishList(@PathVariable Long id, @RequestBody WishListDTO wishListDTO, Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
        Optional<WishListDTO> existingWishList = wishListService.getWishListById(id);
        if (existingWishList.isPresent() && existingWishList.get().getUserId().equals(user.getId())) {
            wishListDTO.setId(id);
            WishListDTO updatedWishList = wishListService.updateWishList(wishListDTO, user);
            return new ResponseEntity<>(updatedWishList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWishList(@PathVariable Long id, Authentication authentication) {
        Long userId = userService.findByEmail(authentication.getName()).getId();
        Optional<WishListDTO> existingWishList = wishListService.getWishListById(id);
        if (existingWishList.isPresent() && existingWishList.get().getUserId().equals(userId)) {
            wishListService.deleteWishList(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
