package com.example.eshoptestapi.service;

import com.example.eshoptestapi.dto.Cart.CartDTO;
import com.example.eshoptestapi.entity.Cart;
import com.example.eshoptestapi.entity.Product;
import com.example.eshoptestapi.entity.User;
import com.example.eshoptestapi.repository.CartRepository;
import com.example.eshoptestapi.repository.ProductRepository;
import com.example.eshoptestapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public List<CartDTO> getAllCartsForUser(Long userId) {
        List<Cart> carts = cartRepository.findByUserId(userId);
        return carts.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<CartDTO> getCartById(Long id) {
        Optional<Cart> cart = cartRepository.findById(id);
        return cart.map(this::convertToDTO);
    }

    public CartDTO addCart(CartDTO cartDTO, User user) {
        Cart cart = convertToEntity(cartDTO);
        cart.setUser(user);
        Cart newCart = cartRepository.save(cart);
        return convertToDTO(newCart);
    }

    public CartDTO updateCart(CartDTO cartDTO, User user) {
        Cart cart = convertToEntity(cartDTO);
        cart.setUser(user);
        Cart updatedCart = cartRepository.save(cart);
        return convertToDTO(updatedCart);
    }

    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }

    private CartDTO convertToDTO(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setUserId(cart.getUser().getId());
        cartDTO.setProductId(cart.getProduct().getProductID());
        cartDTO.setQuantity(cart.getQuantity());
        cartDTO.setCreatedAt(cart.getCreatedAt());
        return cartDTO;
    }

    private Cart convertToEntity(CartDTO cartDTO) {
        Cart cart = new Cart();
        if (cartDTO.getId() != null) {
            cart.setId(cartDTO.getId());
        }
        Optional<Product> product = productRepository.findById(cartDTO.getProductId());
        product.ifPresent(cart::setProduct);
        cart.setQuantity(cartDTO.getQuantity());
        cart.setCreatedAt(cartDTO.getCreatedAt());
        return cart;
    }
}
