package com.scaler.productservice.services;

import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SelfProductServiceTest {

    @Autowired
    private SelfProductService selfProductService;

    @MockBean
    private ProductRepository mockProductRepository;

    @Test
    public void testGetSingleProductIfProductDoesNotExist(){
        Mockito.when(mockProductRepository.findById(-1L)).thenReturn(Optional.empty());


        Assertions.assertThrows(
                ProductNotFoundException.class,
                () -> selfProductService.getSingleProduct(-1L)
        );
    }

}