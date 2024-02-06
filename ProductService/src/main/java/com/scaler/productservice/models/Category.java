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
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE})
    // by default, Spring thinks that this is a separate relation.
    // it is different from what you defined in the Product Model.

    // if you delete a product, delete all the categories as well.
    private List<Product> products;
    private String name;
}

// you want to add Macbook to products table.
// products table depends on the category entry to be made.
// for this, the category should have macbook in its List<Products>.
// category depends upon the product entry to be made.

// M : M
// if Products is a part of multiple categories

// P : C
// 1 : M -> Mapping Table.
// M : 1


// // 1 : M

// product_id | category_id
// 1 -> Dell Laptop  100 -> gaming laptop
// 1                 200 -> office laptop.
// 3 -> shirt        500 -> casual
// 3 -> shirt        600 -> formal

// the above format is a mapping table.

// // M : 1
// product_id           | category_id
// 1000 -> Asus         | 200
// 1050 -> Mac          | 200
// 2000 -> b. jeans     | 500
// 2020 -> black. jeans | 500

// the above format is a mapping table.

// If I combine both of them,
// I still get the same mapping table