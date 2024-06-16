package com.example.eshoptestapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.eshoptestapi.entity.Color;

public interface ColorRepository extends JpaRepository<Color,Long> {
    @Query(value = "SELECT c from Color c")
    List<Color>getAllColor();
    @Query(value ="SELECT c from Color c Where c.id=:id")
    Color getColorById(@Param("id") Long id);
    
}