package com.scaler.productservice.repositories;

import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.queryResponses.ProductWithIdTitlePrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

// CRUD on products
// I want to get all CRUD on Product model!
public interface ProductRepository extends JpaRepository<Product, Long> {

    // jpa buddy.
    List<Product> findTop5DistinctProductByTitleContaining(String name);
    List<Product> findAllByPriceLessThan(double value);
    List<Product> findByPriceBetweenAndTitleLike(double low, double high, String name);
    List<Product> findByTitleAndPriceGreaterThanOrderByTitleAsc(String name, double low);


    // you are given a categoryId, you want to find products whose category is this id.
    // 1.
    // in category, use findById(Long id)
    // here, use
    Product findByCategory(Category category);

    // 2.
    Product findByCategory_Id(Long categoryId);

    // will you always get a product object?
    // it will return NULL if no product found.
    // problem with null? service will throw exception.
    // surprise exceptions.
    // it might not be even present. optional.
    Optional<Product> findById(Long id);    // get single Product

    Product save(Product product);      // add/update product

    @Query("select p from Product p where " +
            "p.price > 100 and p.title like '%iphone%'")
    // you are talking in terms of the class (Product) and
        // not the actual table that is stored.
    // table could be mapped by any name, done by Hibernate.
    // you only know about class here.
    // select p from Product p. means select all the attributes of p.
    List<Product> something();

    @Query("select p.id as id, p.price as price, p.title as title from Product p " +
            "where p.price > 100 and p.title like '%iphone%'")
    List<ProductWithIdTitlePrice> somethingPartial();
}
