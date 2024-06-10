package com.example.eshoptestapi.service;

import com.example.eshoptestapi.entity.Brand;
import com.example.eshoptestapi.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    public Optional<Brand> getBrandById(Long id) {
        return brandRepository.findById(id);
    }

    public Brand createBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    public Brand updateBrand(Long id, Brand brandDetails) {
        Optional<Brand> brandOptional = brandRepository.findById(id);
        if (brandOptional.isPresent()) {
            Brand brand = brandOptional.get();
            brand.setNameBrand(brandDetails.getNameBrand());
            brand.setSlugBrand(brandDetails.getSlugBrand());
            brand.setStatus(brandDetails.getStatus());
            return brandRepository.save(brand);
        } else {
            return null;
        }
    }

    public void deleteBrand(Long id) {
        if (brandRepository.existsById(id)) {
            brandRepository.deleteById(id);
        }
    }
}
