package com.travel.travel.dto;

import com.travel.travel.model.Tour;
import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public class TourAddRequest {
    private int id;
    private String title;
    private String description;
    private int quantity;
    private BigDecimal price;
    private int categoryId;
    private MultipartFile image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
    public static Tour toEntity(TourAddRequest dto){
        Tour entity = new Tour();
        BeanUtils.copyProperties(dto, entity, "image", "categoryId");
        return entity;
    }
    @Override
    public String toString() {
        return "TourAddRequest [id=" + id + ", title=" + title + ", description=" + description + ", quantity="
                + quantity + ", price=" + price + ", categoryId=" + categoryId + "]";
    }
}
