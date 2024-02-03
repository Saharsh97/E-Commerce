package com.scaler.productservice.repositories;

import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
//    Optional<Product> getByTitleContaining(String word);
//    Product findByPriceLessThan(double value);
//    void deleteById(Long id);
//    // select * from products where title like 'word%'
//    // select * from products where price < value
//
//    // given a category Id, I want to fetch all the products with this category.
//
//    List<Product> findByCategory_Name(String name);



    Optional<Product> findById(Long id);

    Product save(Product product);


}
