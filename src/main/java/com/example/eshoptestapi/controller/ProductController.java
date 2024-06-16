package com.example.eshoptestapi.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.eshoptestapi.entity.Product;
import com.example.eshoptestapi.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Product> createProduct( @RequestBody Product newProduct) {
        Product product = productService.createProduct(newProduct);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Product updatedProduct = productService.updateProduct(productDetails);
        return ResponseEntity.ok(updatedProduct);
    } 
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/brand/{brandId}")
    public ResponseEntity<List<Product>> getProductByBrandId(@PathVariable Long brandId) {
        List<Product> products = productService.getProductByBrandId(brandId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/categories/{categoriesId}")
    public ResponseEntity<List<Product>> getProductByCategoriesId(@PathVariable Long categoriesId) {
        List<Product> products = productService.getProductByCategoriesId(categoriesId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/brand/{brandId}/categories/{categoriesId}")
    public ResponseEntity<List<Product>> getProductByBrandIdAndCategoriesId(@PathVariable Long brandId, @PathVariable Long categoriesId) {
        List<Product> products = productService.getProductByBrandIdAndCategoriesId(brandId, categoriesId);
        return ResponseEntity.ok(products);
    }
}