package com.example.eshoptestapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.eshoptestapi.entity.Cart;
import com.example.eshoptestapi.repository.CartRepository;

public class CartService {
    @Autowired
    private CartRepository cartRepository;
    public Cart getCartById(Long id) {
        return cartRepository.getCartbyID(id);
    }
    public List<Cart> getCartByUserIdAndProductId(Long userId, Long productId) {
        return cartRepository.findByUserIdAndProductId(userId, productId);
    }
    //create cart
    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }
    //update cart
    public Cart updateCart(Cart cart) {
        return cartRepository.save(cart);
    }
    //delete cart
    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }
    //get all carts
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }
    
    

    


}
