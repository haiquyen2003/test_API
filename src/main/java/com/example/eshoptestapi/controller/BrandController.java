package com.example.eshoptestapi.controller;

import com.example.eshoptestapi.entity.Brand;
import com.example.eshoptestapi.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

    @Autowired
    private BrandRepository brandRepository;

    // GET all brands
    @GetMapping
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    // GET brand by ID
    @GetMapping("/{id}")
    public ResponseEntity<Brand> getBrandById(@PathVariable Long id) {
        Optional<Brand> brand = brandRepository.findById(id);
        if (brand.isPresent()) {
            return ResponseEntity.ok(brand.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST create new brand
    @PostMapping
    public Brand createBrand(@RequestBody Brand brand) {
        return brandRepository.save(brand);
    }

    // PUT update existing brand
    @PutMapping("/{id}")
    public ResponseEntity<Brand> updateBrand(@PathVariable Long id, @RequestBody Brand brandDetails) {
        Optional<Brand> brandOptional = brandRepository.findById(id);
        if (brandOptional.isPresent()) {
            Brand brand = brandOptional.get();
            brand.setNameBrand(brandDetails.getNameBrand());
            brand.setSlugBrand(brandDetails.getSlugBrand());
            brand.setStatus(brandDetails.getStatus());
            return ResponseEntity.ok(brandRepository.save(brand));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE brand by ID
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
