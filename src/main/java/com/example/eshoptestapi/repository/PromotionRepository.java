package com.example.eshoptestapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.eshoptestapi.entity.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion,Long>{
    @Query(value = "SELECT p FROM Promotion p")
    List<Promotion> getAllPromotions();
    @Query(value = "SELECT p FROM Promotion p WHERE p.id = :id")
    Promotion getPromotionById(@Param("id") Long id);
    @Query(value = "SELECT p FROM Promotion p WHERE p.startDate = :startDate")
    Promotion getPromotionByStartDate(@Param("startDate") String startDate);
    @Query(value = "SELECT p FROM Promotion p WHERE p.endDate = :endDate")
    Promotion getPromotionByEndDate(@Param("endDate") String endDate);

}
