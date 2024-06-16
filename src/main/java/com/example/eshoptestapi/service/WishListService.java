package com.example.eshoptestapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.eshoptestapi.repository.WishlistRepository;
import com.example.eshoptestapi.entity.WishList;;

@Service
public class WishListService {
    @Autowired
    private WishlistRepository wishlistRepository;

    public List<WishList> getAllWishList() {
        return wishlistRepository.findAll();
    }
    public Optional<WishList> getWishListById(Long id) {
        return wishlistRepository.findById(id);
    }
    public WishList createWishList(WishList wishList) {
        return wishlistRepository.save(wishList);
    }
    public WishList updateWishList(Long id, WishList wishListDetails) {
        Optional<WishList> wishListOptional = wishlistRepository.findById(id);
        if (wishListOptional.isPresent()) {
            WishList wishList = wishListOptional.get();
            wishList.setProduct(wishListDetails.getProduct());
            wishList.setUser(wishListDetails.getUser());
            return wishlistRepository.save(wishList);
        } else {
            return null;
        }
    }
    public void deleteWishList(Long id) {
        if (wishlistRepository.existsById(id)) {
            wishlistRepository.deleteById(id);
        }
    }
    


}