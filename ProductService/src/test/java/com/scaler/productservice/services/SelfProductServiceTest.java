package com.scaler.productservice.services;

import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
public class SelfProductServiceTest {

    @Autowired
    private SelfProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void testNonExistingProductThrowsException() throws ProductNotFoundException {
        // what wil I mock?
        // product controller throws exception when product service throws exception.
        // if product repo gives it an empty optional
        // so I will mock ProductRepo here!
        when(productRepository.findById(10L)).thenReturn(Optional.empty());
        // act. do I need to mock anything? to make sure the exception is thrown.

        Assertions.assertThrows(
                ProductNotFoundException.class,
                ()->productService.getSingleProduct(10L)
        );
    }
}
