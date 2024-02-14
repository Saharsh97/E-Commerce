package com.scaler.productservice.controllers;

import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.repositories.ProductRepository;
import com.scaler.productservice.services.IProductService;
import com.scaler.productservice.services.SelfProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

// after break
// what will you add to tell Spring, to create an object of this Test, and run it
@SpringBootTest
class ProductControllerTest {

    // I want to create a real object of ProductController or mock it? Real.
    // Autowire it
    @Autowired
    private ProductController productController;

    @MockBean
    private IProductService productService;


    // what would you want to test in getAllProducts()
    // show the controller method.
    // different scenarios that you want to test.
    // we want to check, if the service is returning all the products,
    // are we also getting all of the products
    // if the service is returning 10 products, we should also get 10 products.
    // when there is not product, I should get an empty list.

    @Test
    // what should we do? AAA.
    // first show the controller method behaviour that we should test
    public void testProductsSameAsService(){
        // arrange inputs.
        // arrange service.
        // add MockBean for productService

        List<Product> products = new ArrayList<>();
        Product p1 = new Product();
        p1.setTitle("iPhone 15");
        products.add(p1);

        Product p2 = new Product();
        p2.setTitle("iPhone 16");
        products.add(p2);

        Product p3 = new Product();
        p3.setTitle("iPhone 17");
        products.add(p3);

        // do this in t2
        List<Product> productsToPass = new ArrayList<>();
        for(Product product: products){
            Product product1 = new Product();
            product1.setTitle(product.getTitle());
            productsToPass.add(product1);
        }


        // when anyone, anywhere calls productService.getAllProducts
        Mockito.when(
                productService.getAllProducts()
        ).thenReturn(
                productsToPass
        );
        // now will the real object of ProductService ever be called?

        // where is the when keyword coming from? Mockito.
        // Mockito is analogous to Junit.
        // Mockito is the de-facto framework for testing in Java.
        // 99% combination of Junit Testing Framework and Mockito Mocking Framework

        // act
        ResponseEntity<List<Product>> response = productController.getAllProducts();
        // internally, productController will try to call productService.getAllProducts().
        // Will the productService.getAllProducts() call DB? now this call is hardcoded by me.
        // so hardcoded response is returned.

        // assert
        // important
        // I want to test that the list of products I got from controller,
        // is it the exact same as the List of products that the service has returned.
        List<Product> productsInResponse = response.getBody();
        // is this correct?
//        Assertions.assertEquals(productsInResponse.size(), products.size());
        // no. The expectation comes first. Then the computed output
        Assertions.assertEquals(products.size(), productsInResponse.size());

        // now go and change the ProductController logic.



        // t2.
        // will the below test case pass? YES
        for(int i = 0; i < productsInResponse.size(); i++){
            Assertions.assertNotEquals(
                    products.get(i).getTitle(),
                    productsInResponse.get(i).getTitle()
            );
        }
        // use products to pass logic now.

    }


    @Test
    public void testNonExistingProductThrowsException() throws ProductNotFoundException {
        // what wil I mock?
        // product controller throws exception when product service throws exception.
        // if product repo gives it an empty optional
        // so I will mock ProductRepo here!
        when(productService.getSingleProduct(10L)).thenThrow(ProductNotFoundException.class);
        // act. do I need to mock anything? to make sure the exception is thrown.

        Assertions.assertThrows(
                ProductNotFoundException.class,
                ()->productController.getSingleProduct(10L)
        );
    }
}