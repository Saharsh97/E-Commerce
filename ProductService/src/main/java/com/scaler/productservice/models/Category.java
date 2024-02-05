package com.scaler.productservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel{
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    // possible scenario. if category deleted, remove all products of this category.
    public List<Product> products;
    private String name;
}
