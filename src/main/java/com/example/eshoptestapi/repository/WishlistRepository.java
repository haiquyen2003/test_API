package com.example.eshoptestapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.eshoptestapi.entity.WishList;

@Repository
public interface WishlistRepository extends JpaRepository<WishList, Long>{
    @Query(value = "SELECT w FROM WishList w")
    List<WishList> getAllWishList();
    @Query(value = "SELECT w FROM WishList w WHERE w.id = :id")
    WishList getWishListById(@Param("id") Long id);
}
