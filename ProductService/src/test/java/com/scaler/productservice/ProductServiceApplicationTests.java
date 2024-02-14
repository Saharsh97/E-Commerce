package com.scaler.productservice;

import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.projections.ProductWithIdTitlePrice;
import com.scaler.productservice.repositories.CategoryRepository;
import com.scaler.productservice.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.List;
import java.util.Optional;

@SpringBootTest
// Junit is the de-facto library for Java test cases.
// Recommended for Java.
// it is so common, that it comes built-in with Spring.
// you dont need to import Junit separately

// Keep the directory analogous to src/main folder.
// have corresponding test class.
// create services package. add SelfProductServiceTest

// This is such a common usage, that you can autogenerate test cases.
// autogenerate for ProductController test
class ProductServiceApplicationTests {

    ProductRepository productRepository;
    CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceApplicationTests(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Test
    // If the Spring startup context didnt load properly, then this test case will fail.
    // hence spring will not at all start
    void contextLoads() {
    }
    // create SpringBootControllerTest

}
