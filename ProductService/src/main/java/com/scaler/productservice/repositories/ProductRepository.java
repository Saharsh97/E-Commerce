package com.scaler.productservice.repositories;

import com.scaler.productservice.models.Product;
import com.scaler.productservice.projections.ProductWithIdTitlePrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> getByTitleContaining(String word);
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
    Product something(@Param("id") Long id);

    @Query("select p.id as id, p.title as title, p.price as price from Product p")
    List<ProductWithIdTitlePrice> somethingSpecific();

    @Query(value = "select * from product p where p.id = :id", nativeQuery = true)
    // you have to be careful here.
    // intellij doesnt know the exact column names.
    // in previous case, intellij knew the columns, as you had model references.
    // this is tightly coupled to your DB.
    // if your DB changes, will you change the query? yes.
    // we use it for more control, but tight coupling.
    // in older case, its less control, but less coupling as well.
    // joins also possible with this.
    Product somethingExactSQL(@Param("id") Long id);

    @Query(value = "select p.id as id, p.title as title, p.price as price from product p where p.id = 52", nativeQuery = true)
    List<ProductWithIdTitlePrice> somethingExactSQLFields();
}
