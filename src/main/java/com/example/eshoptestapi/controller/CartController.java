package com.example.eshoptestapi.controller;

import com.example.eshoptestapi.dto.Cart.CartDTO;
import com.example.eshoptestapi.entity.User;
import com.example.eshoptestapi.service.CartService;
import com.example.eshoptestapi.service.UserService;
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
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping
    public ResponseEntity<List<CartDTO>> getAllCarts(Authentication authentication) {
        Long userId = userService.findByEmail(authentication.getName()).getId();
        List<CartDTO> carts = cartService.getAllCartsForUser(userId);
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/{id}")
    public ResponseEntity<CartDTO> getCartById(@PathVariable Long id, Authentication authentication) {
        Long userId = userService.findByEmail(authentication.getName()).getId();
        Optional<CartDTO> cart = cartService.getCartById(id);
        if (cart.isPresent() && cart.get().getUserId().equals(userId)) {
            return new ResponseEntity<>(cart.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping
    public ResponseEntity<CartDTO> addCart(@RequestBody CartDTO cartDTO, Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
        cartDTO.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        CartDTO newCart = cartService.addCart(cartDTO, user);
        return new ResponseEntity<>(newCart, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PutMapping("/{id}")
    public ResponseEntity<CartDTO> updateCart(@PathVariable Long id, @RequestBody CartDTO cartDTO, Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
        Optional<CartDTO> existingCart = cartService.getCartById(id);
        if (existingCart.isPresent() && existingCart.get().getUserId().equals(user.getId())) {
            cartDTO.setId(id);
            CartDTO updatedCart = cartService.updateCart(cartDTO, user);
            return new ResponseEntity<>(updatedCart, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id, Authentication authentication) {
        Long userId = userService.findByEmail(authentication.getName()).getId();
        Optional<CartDTO> existingCart = cartService.getCartById(id);
        if (existingCart.isPresent() && existingCart.get().getUserId().equals(userId)) {
            cartService.deleteCart(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
