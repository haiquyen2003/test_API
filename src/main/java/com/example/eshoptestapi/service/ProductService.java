package com.example.eshoptestapi.service;

import com.example.eshoptestapi.entity.Brand;
import com.example.eshoptestapi.entity.Categories;
import com.example.eshoptestapi.entity.Product;
import com.example.eshoptestapi.entity.Promotion;
import com.example.eshoptestapi.repository.BrandRepository;
import com.example.eshoptestapi.repository.CategoriesRepository;
import com.example.eshoptestapi.repository.ProductRepository;
import com.example.eshoptestapi.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Autowired
    private PromotionRepository promotionRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product addProduct(Product product) {
        if (product.getBrand() != null && product.getBrand().getId() != null) {
            Optional<Brand> brand = brandRepository.findById(product.getBrand().getId());
            brand.ifPresent(product::setBrand);
        }

        if (product.getCategories() != null && product.getCategories().getId() != null) {
            Optional<Categories> categories = categoriesRepository.findById(product.getCategories().getId());
            categories.ifPresent(product::setCategories);
        }

        if (product.getPromotion() != null && product.getPromotion().getPromotionID() != null) {
            Optional<Promotion> promotion = promotionRepository.findById(product.getPromotion().getPromotionID());
            promotion.ifPresent(product::setPromotion);
        }

        return productRepository.save(product);
    }

    public Product updateProduct(Product product) {
        if (product.getBrand() != null && product.getBrand().getId() != null) {
            Optional<Brand> brand = brandRepository.findById(product.getBrand().getId());
            brand.ifPresent(product::setBrand);
        }

        if (product.getCategories() != null && product.getCategories().getId() != null) {
            Optional<Categories> categories = categoriesRepository.findById(product.getCategories().getId());
            categories.ifPresent(product::setCategories);
        }

        if (product.getPromotion() != null && product.getPromotion().getPromotionID() != null) {
            Optional<Promotion> promotion = promotionRepository.findById(product.getPromotion().getPromotionID());
            promotion.ifPresent(product::setPromotion);
        }

        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
