package com.example.eshoptestapi.repository;
import com.example.eshoptestapi.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
        @Query(value = "SELECT c FROM Cart c WHERE c.id = :id")
        Cart getCartbyID(@Param("id") Long id);
        
        @Query(value = "SELECT c FROM Cart c WHERE c.user.id = :userId AND c.productId.id = :productId")
        List<Cart> findByUserIdAndProductId(@Param("userId") Long userId, @Param("productId") Long productId);
        
        @Modifying
        @Query(value = "DELETE FROM Cart c WHERE c.user.id = :userId AND c.productId.id = :productId")
        void deleteByUserIdAndProductId(@Param("userId") Long userId, @Param("productId") Long productId);
        
        @Modifying
        @Query(value = "DELETE FROM Cart c WHERE c.user.id = :userId")
        void deleteByUserId(@Param("userId") Long userId);
        
        @Query(value = "SELECT c FROM Cart c WHERE c.user.id = :userId")
        List<Cart> findByUserId(@Param("userId") Long userId);
        
        @Modifying
        @Query(value = "DELETE FROM Cart c WHERE c.id = :id")
        void deleteById(@Param("id") Long id);
        //find all
        @Query(value = "SELECT c FROM Cart c")
        List<Cart> findAll();
       
        
}