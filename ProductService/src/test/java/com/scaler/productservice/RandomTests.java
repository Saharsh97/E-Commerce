package com.scaler.productservice;

import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.services.SelfProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

@SpringBootTest
public class RandomTests {

    @Autowired
    private SelfProductService selfProductService;

    @Test
    public void onePlusOneIsTwo(){
        // Arrange
        int a = 1;
        int b = 1;

        // Act
        int x = a + b;

        // Assert
//        assert x == 3;
//        Assertions.assertTrue(x==2);

        // Expectations first, then the response later
        Assertions.assertEquals(2, x);
        Assertions.assertNotEquals();

        Assertions.assertNull();
        Assertions.assertNotNull();

        int[] input = new int[]{2, 8, 9, 3, 4, 0, 1};
        int[] expectedOutput = new int[]{0, 1, 2, 3, 4, 8, 9};
        int[] response = mySortMethod(input);

        Assertions.assertArrayEquals(expectedOutput, response);

        List<String> expectations = List.of("abc", "xyz");
        List<String> responseList = myStringMethod();

        Assertions.assertLinesMatch(expectations, responseList);

        Assertions.assertTimeout(
                Duration.of(2L, ChronoUnit.SECONDS),
                ()->selfProductService.getAllProducts()
        );

        Assertions.assertThrows(
                ProductNotFoundException.class,
                () -> selfProductService.getSingleProduct(100L)
        );

    }

}
