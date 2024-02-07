package com.scaler.productservice.repositories;

import com.scaler.productservice.models.Product;
import com.scaler.productservice.projections.ProductWithIdTitlePrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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


    @Query("select p from Product p where p.id = :id")
    List<Product> something(@Param("id") Long id);

    @Query("select p.id as id, p.title as title, p.price as price from Product p where p.id = :id")
    List<ProductWithIdTitlePrice> somethingSpecific(@Param("id") Long id);

    @Query(value = "select * from product where product.id = :id", nativeQuery = true)
    List<Product> somethingSQL(@Param("id") Long id);

    @Query(value = "select p.id as id, p.title as title, p.price as price from product p where p.id = :id", nativeQuery = true)
    List<ProductWithIdTitlePrice> somethingSQLWithFields(@Param("id") Long id);

    // HQL Queries: Loosely coupled with the DB.
    // if DB changes, query is unchanged.
    // it gives a lesser on the query.
    // alias for table is required!

    // Native queries: Tight coupling with the DB.
    // if DB changes, query also changes.
    // it gives the exact control on the query!
    // Alias is not necessary
}
