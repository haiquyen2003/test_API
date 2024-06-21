package com.example.eshoptestapi.service;

import com.example.eshoptestapi.dto.Color.ColorDTO;
import com.example.eshoptestapi.entity.Color;
import com.example.eshoptestapi.repository.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ColorService {

    @Autowired
    private ColorRepository colorRepository;

    public List<ColorDTO> getAllColors() {
        List<Color> colors = colorRepository.findAll();
        return colors.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<ColorDTO> getColorById(Long id) {
        Optional<Color> color = colorRepository.findById(id);
        return color.map(this::convertToDTO);
    }

    public ColorDTO addColor(ColorDTO colorDTO) {
        Color color = convertToEntity(colorDTO);
        Color newColor = colorRepository.save(color);
        return convertToDTO(newColor);
    }

    public ColorDTO updateColor(ColorDTO colorDTO) {
        Optional<Color> colorOptional = colorRepository.findById(colorDTO.getId());
        if (colorOptional.isPresent()) {
            Color color = colorOptional.get();
            color.setNameColor(colorDTO.getNameColor());
            Color updatedColor = colorRepository.save(color);
            return convertToDTO(updatedColor);
        } else {
            throw new RuntimeException("Color not found");
        }
    }

    public void deleteColor(Long id) {
        colorRepository.deleteById(id);
    }

    private ColorDTO convertToDTO(Color color) {
        ColorDTO colorDTO = new ColorDTO();
        colorDTO.setId(color.getId());
        colorDTO.setNameColor(color.getNameColor());
        return colorDTO;
    }

    private Color convertToEntity(ColorDTO colorDTO) {
        Color color = new Color();
        if (colorDTO.getId() != null) {
            color.setId(colorDTO.getId());
        }
        color.setNameColor(colorDTO.getNameColor());
        return color;
    }
}
