package com.example.eshoptestapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.eshoptestapi.entity.Color;
import com.example.eshoptestapi.repository.ColorRepository;

@Service
public class ColorService {
    @Autowired
    private ColorRepository colorRepository;

    public List<Color> getAllColor(){
        return colorRepository.findAll();
    }

    public Optional<Color> getColorById(Long id){
        return colorRepository.findById(id);
    }
    public Color createColor(Color color){
        return colorRepository.save(color);
    }
    public Color updateColor(Long id,Color colorDetails){
        Optional<Color> colorOptional = colorRepository.findById(id);
        if(colorOptional.isPresent()){
            Color color = colorOptional.get();
            color.setNameColor(colorDetails.getNameColor());
            return colorRepository.save(color);
        }else{
            return null;
        }
    }
    public void deleteColor(Long id){
        if(colorRepository.existsById(id)){
            colorRepository.deleteById(id);
        }
    }

    

    
    
    
}
