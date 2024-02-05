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
        Product product = productRepository.something(53L);
        System.out.println(product.getId() + " : " + product.getTitle());
    }

    @Test
    void getProjections(){
        List<ProductWithIdTitlePrice> products = productRepository.somethingSpecific();
        for(ProductWithIdTitlePrice p: products){
            System.out.println(p.getId() + " : " + p.getTitle() + " : " + p.getPrice());
        }
    }

    @Test
    void getSQLQueryAnswer(){
        Product product = productRepository.somethingExactSQL(52L);

        System.out.println(product.getId() + " : " + product.getTitle() + " : " + product.getPrice());
        System.out.println();
    }

    @Test
    void getSQLQueryAnswerFields(){
        List<ProductWithIdTitlePrice> products = productRepository.somethingExactSQLFields();
        for(ProductWithIdTitlePrice p: products){
            System.out.println(p.getId() + " : " + p.getTitle() + " : " + p.getPrice());
            System.out.println();
        }
    }

    @Test
    @Transactional
    @Commit
    void deleteEntries(){
        productRepository.deleteById(300L);
    }

    @Test
    void checkCategoryEagerFetch(){
        categoryRepository.findById(2L);    // show: join in query.
    }

    @Test
    @Transactional
    void checkCategoryLazyFetch(){
        Optional<Category> categoryOptional = categoryRepository.findById(2L);
        // show: no join used in query.
        System.out.println("doing some work...");
        // ----------------------------------- //
        // ----------------------------------- //
        // ----------------------------------- //
        Category category = categoryOptional.get();

        List<Product> products = category.getProducts();
        // join query done, only when you call getProducts()
        Product product = products.get(0);
        System.out.println(product.getId());
    }
}
