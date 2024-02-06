package com.scaler.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel{
    @OneToMany(mappedBy = "category")
    // by default, Spring thinks that this is a separate relation.
    // it is different from what you defined in the Product Model.
    private List<Product> products;
    private String name;
}

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