package com.example.eshoptestapi.repository;

import com.example.eshoptestapi.entity.Categories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Categories,Long> {
        @Query(value = "SELECT c FROM Categories c")
        List<Categories> getAllCategory();
        @Query(value = "SELECT c FROM Categories c WHERE c.id = :id")
        Categories getCategoryById(@Param("id") Long id);
    
}