package com.scaler.productservice.controllers;

import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.services.IProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    @Qualifier("selfProductService")
    private IProductService mockProductService;

    // I want to test, that
    // whatever list I get from ProductService.getAllProducts()
    // the controller should return the same list.
    @Test
    public void testGetAllProducts(){

        // service will give me a list of products.
        // arrange
        List<Product> hardcodedProductsList = new ArrayList<>();
        Product p1 = new Product();
        p1.setTitle("iPhone 15");
        hardcodedProductsList.add(p1);

        Product p2 = new Product();
        p2.setTitle("iPhone 16");
        hardcodedProductsList.add(p2);

        Product p3 = new Product();
        p3.setTitle("iPhone 17");
        hardcodedProductsList.add(p3);

        List<Product> productsToPass = new ArrayList<>();
        for(Product hardcodedProduct: hardcodedProductsList){
            Product copyProduct = new Product();
            copyProduct.setTitle(hardcodedProduct.getTitle());
            productsToPass.add(copyProduct);
        }

        Mockito.when(mockProductService.getAllProducts()).thenReturn(productsToPass);


        // act
        ResponseEntity<List<Product>> responseEntity = productController.getAllProducts();
        List<Product> responseProductsFromController = responseEntity.getBody();

        // assert:
        Assertions.assertEquals(hardcodedProductsList.size(), responseProductsFromController.size());

        for(int i = 0; i < hardcodedProductsList.size(); i++){
            Assertions.assertEquals(hardcodedProductsList.get(i).getTitle(), responseProductsFromController.get(i).getTitle());
        }
    }

    // if the service throws exception
    // behaviour: my controller should also throw an exception.
    // I want to test this behaviour.
    @Test
    public void testControllerThrowsExceptionIfProductDoesNotExist() throws ProductNotFoundException {
        // arrange

        Mockito.when(mockProductService.getSingleProduct(-1L))
                .thenThrow(ProductNotFoundException.class);

        // act and assert

        Assertions.assertThrows(
                ProductNotFoundException.class,
                ()->productController.getSingleProduct(-1L)
        );
    }
}