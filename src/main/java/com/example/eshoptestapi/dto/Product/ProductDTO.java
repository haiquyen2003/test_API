package com.example.eshoptestapi.dto.Product;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class ProductDTO {

    private Long productID;
    private Long brandID;
    private Long categoriesID;
    private Long promotionID;
    private String description;
    private BigDecimal discountPrice;
    private BigDecimal price;
    private String image1;
    private String image2;
    private String image3;
    private String information;
    private String sku;
    private String slugProduct;
    private Integer stock;
    private String summary;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // Getters and setters

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public Long getBrandID() {
        return brandID;
    }

    public void setBrandID(Long brandID) {
        this.brandID = brandID;
    }

    public Long getCategoriesID() {
        return categoriesID;
    }

    public void setCategoriesID(Long categoriesID) {
        this.categoriesID = categoriesID;
    }

    public Long getPromotionID() {
        return promotionID;
    }

    public void setPromotionID(Long promotionID) {
        this.promotionID = promotionID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getSlugProduct() {
        return slugProduct;
    }

    public void setSlugProduct(String slugProduct) {
        this.slugProduct = slugProduct;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
