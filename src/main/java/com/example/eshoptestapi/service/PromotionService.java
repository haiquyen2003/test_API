package com.example.eshoptestapi.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eshoptestapi.entity.Promotion;
import com.example.eshoptestapi.repository.PromotionRepository;

@Service
public class PromotionService {
    @Autowired
    private PromotionRepository promotionRepository;
    public List<Promotion> getAllPromotions(){
        return promotionRepository.findAll();
    }
    public Optional<Promotion> getPromotionById(Long id){
        return promotionRepository.findById(id);
    }
    public Promotion createPromotion(Promotion promotion){
        return promotionRepository.save(promotion);
    }
    public Promotion updatePromotion(Long id,Promotion promotionDetails){
        Optional<Promotion> promotionOptional = promotionRepository.findById(id);
        if(promotionOptional.isPresent()){
            Promotion promotion = promotionOptional.get();
            promotion.setDiscount(promotionDetails.getDiscount());
            promotion.setStartDate(promotionDetails.getStartDate());
            promotion.setEndDate(promotionDetails.getEndDate());

            return promotionRepository.save(promotion);
        }else{
            return null;
        }
    }
    public void deletePromotion(Long id){
        if(promotionRepository.existsById(id)){
            promotionRepository.deleteById(id);
        }
    }
    
    
}
