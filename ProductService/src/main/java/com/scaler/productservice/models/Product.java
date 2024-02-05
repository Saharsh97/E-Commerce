package com.scaler.productservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Product extends BaseModel{

    private String title;
    private String description;
    private double price;
    private String imageUrl;

    @ManyToOne (cascade = {CascadeType.ALL})
    // whatever happens to product, do the exact same to category.
    // if product created with new category, create new category.
    // if product delete, delete category as well.
    // Id of Category, on the Product table.
    // should you cascade delete as well? No. but this does it.
    // add product, auto creates the category.

//    @ManyToOne (cascade = {CascadeType.PERSIST})
    // only cascade the PERSIST. dont cascade the delete.

//    @ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    // cascade saving, and cascade delete as well.

    // basically says, what all things do you want to cascade.
//    @ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    // save and update cascade
    private Category category;
}
