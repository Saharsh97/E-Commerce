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


    public ProductServiceApplicationTests(){

    }

    // has the Spring context (container, which contains all the Spring beans),
    // loaded properly or not, before taking any incoming request.
    @Test
    void contextLoads() {

    }
}

// you click on start Application
// Spring will create a container.
// Spring create the beans, and container stores all the beans
// we have a bean called ProductServiceApplicationTests.
// Spring ensures that all @Test methods run first,
// if all successful, you take incoming request.
// even if you dont write any testcase, you still have 1 test case : contextLoads()


// if the applications gets any error while starting
// then this contextLoads() will not run.

// Spring ensures that all @Test methods run first,
// but this testcase did not run.

//something is wrong!
// dont start the application.


// break till 8:25
