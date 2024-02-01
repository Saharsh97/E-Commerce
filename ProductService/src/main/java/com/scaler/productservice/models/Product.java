package com.scaler.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Product extends BaseModel{

    private String name;
    private String description;
    private double price;
    private String imageUrl;

    // every product has only one category.
    // M : 1
    @ManyToOne
    // Id of Category, on the Product table.
    private Category category;
}
