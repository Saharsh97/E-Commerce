package com.scaler.productservice;

import com.scaler.productservice.models.Product;
import com.scaler.productservice.queryResponses.ProductWithIdTitlePrice;
import com.scaler.productservice.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.List;

@SpringBootTest
class ProductServiceApplicationTests {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceApplicationTests(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Test
    void contextLoads() {
    }

    // typically test results are rolledback.
    // if you dont want to rollback, use @Commit.
    // logs print ?, for privacy concerns.
    // anyone can read the parameters.
    @Test
    @Transactional
    @Commit
    void testReadQueries(){

    }

    @Test
    @Transactional
    @Commit
    void testDeleteQueries(){
        // delete product
    }


    // get
    @Test
    void testHQLQueries(){
        List<Product> productList =
                productRepository.something();
        for(Product p: productList){
            System.out.println(p.getTitle() + " : " + p.getPrice());
        }
    }

    @Test
    void testHQLQueriesPartial(){
        List<ProductWithIdTitlePrice> productList =
                productRepository.somethingPartial();
        for(ProductWithIdTitlePrice p: productList){
            System.out.println(p.getTitle() + " : " + p.getPrice());
        }
    }
}
