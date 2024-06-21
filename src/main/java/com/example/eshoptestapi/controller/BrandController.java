package com.example.eshoptestapi.controller;

import com.example.eshoptestapi.entity.Brand;
import com.example.eshoptestapi.entity.Categories;
import com.example.eshoptestapi.repository.BrandRepository;
import com.example.eshoptestapi.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoriesRepository categoriesRepository; // Đảm bảo rằng CategoriesRepository được khai báo và autowire

    // GET all brands (Cho phép tất cả mọi người xem)
    @GetMapping
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    // GET brand by ID (Cho phép tất cả mọi người xem)
    @GetMapping("/{id}")
    public ResponseEntity<Brand> getBrandById(@PathVariable Long id) {
        Optional<Brand> brand = brandRepository.findById(id);
        if (brand.isPresent()) {
            return ResponseEntity.ok(brand.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST create new brand (Chỉ dành cho Admin)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Brand> createBrand(@RequestBody Brand brand) {
        if (brand.getCategory() == null || brand.getCategory().getId() == null) {
            return ResponseEntity.badRequest().body(null);
        }
        Optional<Categories> categoryOpt = categoriesRepository.findById(brand.getCategory().getId());
        if (!categoryOpt.isPresent()) {
            return ResponseEntity.badRequest().body(null);
        }
        brand.setCategory(categoryOpt.get());
        Brand savedBrand = brandRepository.save(brand);
        return ResponseEntity.ok(savedBrand);
    }

    // PUT update existing brand (Chỉ dành cho Admin)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Brand> updateBrand(@PathVariable Long id, @RequestBody Brand brandDetails) {
        Optional<Brand> brandOptional = brandRepository.findById(id);
        if (brandOptional.isPresent()) {
            Brand brand = brandOptional.get();
            brand.setNameBrand(brandDetails.getNameBrand());
            brand.setSlugBrand(brandDetails.getSlugBrand());
            brand.setStatus(brandDetails.getStatus());
            if (brandDetails.getCategory() != null && brandDetails.getCategory().getId() != null) {
                Optional<Categories> categoryOpt = categoriesRepository.findById(brandDetails.getCategory().getId());
                categoryOpt.ifPresent(brand::setCategory);
            }
            return ResponseEntity.ok(brandRepository.save(brand));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE brand by ID (Chỉ dành cho Admin)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
        if (brandRepository.existsById(id)) {
            brandRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
