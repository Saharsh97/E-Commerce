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
class ProductServiceApplicationTests {

    ProductRepository productRepository;
    CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceApplicationTests(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
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

//    @Test
//    @Transactional
//    void getCategoryDetails(){
//        Optional<Category> categoryOptional = categoryRepository.findById(2L);
//        Category category = categoryOptional.get();
//        System.out.println(category.getName());
//        List<Product> products = category.getProducts();
//        for(Product p: products){
//            System.out.println(p.getId() + " : " + p.getPrice());
//        }
//    }
    // what is faster?
    // 2 DB calls OR 1 DB with JOIN?
    // 2 DB calls will be slow.
    // 2 DB calls in 5% of the cases, is better a JOIN in 100% of cases.

    // lazy fetch
    // in 95% scenarios, you only want to get category.name() -> 1 DB call without join.
    // in only 5% scenarios, you want to get the category.name() + category.products() -> 2 DB calls.

    // eager fetch.
    // in 95% scenarios, you only want to get category.name() -> 1 DB call with join.
    // you are wasting time taking join on the products, when it is not even needed!

    // FetchMode. This is not relevant. because your ORM ignores FetchMode.
    @Test
    @Transactional
    @Commit
    void deleteEntries(){
        productRepository.deleteById(300L);
    }
}
