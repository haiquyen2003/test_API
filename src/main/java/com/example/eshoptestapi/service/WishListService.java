package com.example.eshoptestapi.service;

import com.example.eshoptestapi.dto.WishList.WishListDTO;
import com.example.eshoptestapi.entity.Product;
import com.example.eshoptestapi.entity.User;
import com.example.eshoptestapi.entity.WishList;
import com.example.eshoptestapi.repository.ProductRepository;
import com.example.eshoptestapi.repository.UserRepository;
import com.example.eshoptestapi.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WishListService {

    @Autowired
    private WishListRepository wishListRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public List<WishListDTO> getAllWishListsForUser(Long userId) {
        List<WishList> wishLists = wishListRepository.findByUserId(userId);
        return wishLists.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<WishListDTO> getWishListById(Long id) {
        Optional<WishList> wishList = wishListRepository.findById(id);
        return wishList.map(this::convertToDTO);
    }

    public WishListDTO addWishList(WishListDTO wishListDTO, User user) {
        WishList wishList = convertToEntity(wishListDTO);
        wishList.setUser(user);
        WishList newWishList = wishListRepository.save(wishList);
        return convertToDTO(newWishList);
    }

    public WishListDTO updateWishList(WishListDTO wishListDTO, User user) {
        WishList wishList = convertToEntity(wishListDTO);
        wishList.setUser(user);
        WishList updatedWishList = wishListRepository.save(wishList);
        return convertToDTO(updatedWishList);
    }

    public void deleteWishList(Long id) {
        wishListRepository.deleteById(id);
    }

    private WishListDTO convertToDTO(WishList wishList) {
        WishListDTO wishListDTO = new WishListDTO();
        wishListDTO.setId(wishList.getId());
        wishListDTO.setProductId(wishList.getProduct().getProductID());
        wishListDTO.setUserId(wishList.getUser().getId()); // Thêm thuộc tính userId
        wishListDTO.setCreatedAt(wishList.getCreatedAt());
        return wishListDTO;
    }

    private WishList convertToEntity(WishListDTO wishListDTO) {
        WishList wishList = new WishList();
        if (wishListDTO.getId() != null) {
            wishList.setId(wishListDTO.getId());
        }
        Optional<Product> product = productRepository.findById(wishListDTO.getProductId());
        product.ifPresent(wishList::setProduct);
        wishList.setCreatedAt(wishListDTO.getCreatedAt());
        return wishList;
    }
}
