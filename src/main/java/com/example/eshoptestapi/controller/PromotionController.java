package com.example.eshoptestapi.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.eshoptestapi.entity.Promotion;
import com.example.eshoptestapi.repository.PromotionRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/promotions")
public class PromotionController {
    @Autowired
    private PromotionRepository promotionRepository;
    @GetMapping("/{id}")
    public ResponseEntity<Promotion> getPromotionById(@PathVariable Long id){
        Optional<Promotion> promotion = promotionRepository.findById(id);
        if(promotion.isPresent()){
            return ResponseEntity.ok(promotion.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping
    public List<Promotion> getAllPromotions(){
        return promotionRepository.findAll();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Promotion createPromotion(@RequestBody Promotion promotion){
        return promotionRepository.save(promotion);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Promotion> updatePromotion(@PathVariable Long id,@RequestBody Promotion promotionDetails){
        Optional<Promotion> promotionOptional = promotionRepository.findById(id);
        if(promotionOptional.isPresent()){
            Promotion promotion = promotionOptional.get();
            promotion.setDiscount(promotionDetails.getDiscount());
            promotion.setStartDate(promotionDetails.getStartDate());
            promotion.setEndDate(promotionDetails.getEndDate());
            return ResponseEntity.ok(promotionRepository.save(promotion));
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePromotion(@PathVariable Long id){
        if(promotionRepository.existsById(id)){
            promotionRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    
    
        
}
