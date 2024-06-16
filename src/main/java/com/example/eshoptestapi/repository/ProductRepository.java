package com.example.eshoptestapi.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.eshoptestapi.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT p FROM Product p WHERE p.id = :id")
    Product getProductbyID(@Param("id") Long id);
    
    @Query(value = "SELECT p FROM Product p WHERE p.brand.id = :brandId")
    List<Product> findByBrandId(@Param("brandId") Long brandId);
    
    @Query(value = "SELECT p FROM Product p WHERE p.categories.id = :categoriesId")
    List<Product> findByCategoriesId(@Param("categoriesId") Long categoriesId);
    
    @Query(value = "SELECT p FROM Product p WHERE p.brand.id = :brandId AND p.categories.id = :categoriesId")
    List<Product> findByBrandIdAndCategoriesId(@Param("brandId") Long brandId, @Param("categoriesId") Long categoriesId);
    
    @Modifying
    @Query(value = "DELETE FROM Product p WHERE p.id = :id")
    void deleteById(@Param("id") Long id);
    @Query(value = "SELECT p FROM Product p")
    List<Product> findAll();
    
    @Query(value = "SELECT p FROM Product p WHERE p.productName = :productName")
    List<Product> findByNameProduct(@Param("productName") String productName);
    
    @Query(value = "SELECT p FROM Product p WHERE p.slugProduct = :slugProduct")
    List<Product> findBySlugProduct(@Param("slugProduct") String slugProduct);
    
    @Query(value = "SELECT p FROM Product p WHERE p.stock = :stock")
    List<Product> findByStatus(@Param("stock") String stock);
    
    @Query(value = "SELECT p FROM Product p WHERE p.price = :price")
    List<Product> findByPrice(@Param("price") Double price);
    
    @Query(value = "SELECT p FROM Product p WHERE p.discountPrice = :discountPrice")
    List<Product> findByDiscount(@Param("discountPrice") Double discountPrice);
    
    @Query(value = "SELECT p FROM Product p WHERE p.price = :price AND p.discountPrice = :discountPrice")
    List<Product> findByPriceAndDiscount(@Param("price") Double price, @Param("discountPrice") Double discountPrice);
    
    @Query(value = "SELECT p FROM Product p WHERE p.price = :price AND p.discountPrice = :discountPrice AND p.stock = :stock")
    List<Product> findByPriceAndDiscountAndStatus(@Param("price") Double price, @Param("discountPrice") Double discountPrice, @Param("stock") String stock);
}


