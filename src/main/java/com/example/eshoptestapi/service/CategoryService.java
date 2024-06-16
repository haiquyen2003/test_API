package com.example.eshoptestapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eshoptestapi.entity.Categories;
import com.example.eshoptestapi.repository.CategoryRepository;
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Categories> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Categories getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public Categories createCategory(Categories category) {
        return categoryRepository.save(category);
    }

    public Categories updateCategory(Long id, Categories categoryDetails) {
        Categories category = categoryRepository.findById(id).orElse(null);
        if (category != null) {
            category.setNameCategory(categoryDetails.getNameCategory());
            category.setSlugCategory(categoryDetails.getSlugCategory());
            return categoryRepository.save(category);
        } else {
            return null;
        }
    }

    public void deleteCategory(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        }
    }

   

}
