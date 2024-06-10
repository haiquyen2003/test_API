package com.example.eshoptestapi.entity;

import jakarta.persistence.*;

@Entity
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_brand", length = 50, nullable = false)
    private String nameBrand;

    @Column(name = "slug_brand", length = 50, nullable = false)
    private String slugBrand;

    @Column(name = "status", length = 10, nullable = false)
    private String status;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameBrand() {
        return nameBrand;
    }

    public void setNameBrand(String nameBrand) {
        this.nameBrand = nameBrand;
    }

    public String getSlugBrand() {
        return slugBrand;
    }

    public void setSlugBrand(String slugBrand) {
        this.slugBrand = slugBrand;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
