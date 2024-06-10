package com.example.eshoptestapi.entity;

import jakarta.persistence.*;

@Entity
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_category", length = 200)
    private String imageCategory;

    @Column(name = "name_category", length = 50, nullable = false)
    private String nameCategory;

    @Column(name = "slug_category", length = 50, nullable = false)
    private String slugCategory;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageCategory() {
        return imageCategory;
    }

    public void setImageCategory(String imageCategory) {
        this.imageCategory = imageCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getSlugCategory() {
        return slugCategory;
    }

    public void setSlugCategory(String slugCategory) {
        this.slugCategory = slugCategory;
    }
}
