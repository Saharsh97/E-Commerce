package com.scaler.productservice;

import com.scaler.productservice.models.Product;
import com.scaler.productservice.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

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
        Product product = productRepository.findByPriceLessThan(20000.0);
        System.out.println(product.getPrice());
    }

    @Test
    @Transactional
    @Commit
    void deleteEntries(){
        productRepository.deleteById(300L);
    }
}
