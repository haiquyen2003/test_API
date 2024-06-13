package com.example.eshoptestapi.service;

import com.example.eshoptestapi.entity.Categories;
import com.example.eshoptestapi.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService {

    @Autowired
    private CategoriesRepository categoriesRepository;

    public List<Categories> getAllCategories() {
        return categoriesRepository.findAll();
    }

    public Categories getCategoryById(Long id) {
        return categoriesRepository.findById(id).orElse(null);
    }

    public Categories saveCategory(Categories category) {
        return categoriesRepository.save(category);
    }

    public Categories updateCategory(Long id, Categories categoryDetails) {
        return categoriesRepository.findById(id).map(category -> {
            category.setNameCategory(categoryDetails.getNameCategory());
            category.setImageCategory(categoryDetails.getImageCategory());
            category.setSlugCategory(categoryDetails.getSlugCategory());
            return categoriesRepository.save(category);
        }).orElse(null);
    }

    public boolean deleteCategory(Long id) {
        return categoriesRepository.findById(id).map(category -> {
            categoriesRepository.delete(category);
            return true;
        }).orElse(false);
    }
}
