package com.scaler.productservice;

import com.scaler.productservice.models.Product;
import com.scaler.productservice.projections.ProductWithIdTitlePrice;
import com.scaler.productservice.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProductServiceApplicationTests {

    ProductRepository productRepository;

    @Autowired
    public ProductServiceApplicationTests(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @Test
    void contextLoads() {
    }

    @Test
    void getData(){
        List<Product> products = productRepository.something(52L);
        for(Product p: products){
            System.out.println(p.getId() + " " + p.getTitle());
        }
    }

    @Test
    void getSpecificData(){
        List<ProductWithIdTitlePrice> products = productRepository.somethingSpecific(52L);
        for(ProductWithIdTitlePrice p: products){
            System.out.println(p.getId() + " " + p.getTitle());
        }
    }

    @Test
    void getDataUsingSQLQuery(){
        List<Product> products = productRepository.somethingSQL(52L);
        for(Product p: products){
            System.out.println(p.getId() + " " + p.getTitle());
        }
    }

    @Test
    void getDataUsingSQLQueryFields(){
        List<ProductWithIdTitlePrice> products = productRepository.somethingSQLWithFields(52L);
        for(ProductWithIdTitlePrice p: products){
            System.out.println(p.getId() + " " + p.getTitle());
        }
    }

    @Test
    @Transactional
    @Commit
    void deleteEntries(){
        productRepository.deleteById(300L);
    }
}
