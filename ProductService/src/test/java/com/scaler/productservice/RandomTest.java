//package com.scaler.productservice;
//
//import com.scaler.productservice.controllers.ProductController;
//import com.scaler.productservice.exceptions.ProductNotFoundException;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.Duration;
//import java.time.temporal.ChronoUnit;
//import java.time.temporal.TemporalUnit;
//
//@SpringBootTest
//public class RandomTest {
//    // a testcase is nothing but a method.
//    // a testcase method is doing following things. AAA
//    // arrange, act, asset
//
//    @Autowired
//    ProductController productController;
//
//    @Test
//    // In spring, you want to do something special, that is? annotation
//    // adding @Test will automatically add play button
//    void onePlusOneIsTwo(){
//        // arrange
//        int a = 1;
//        int b = 1;
//
//        // act
//        int x = a + b;
//
//        // assert
//        assert x == 3; // fails
//        assert x == 2; // passes.
//
//        // a testcase fails when any of the assetions fails.
//        // a testcase passes when all of the assertions passes.
//
//        // tomorrow if the behaviour of + changes, test fails.
//
//        // we may want to have other types of assertions.
//        // assert if Correct type of Exception is thrown.
//        // if you want to do this, will this assert work? no.
//        // it just gives true or false.
//
//        // so we need an Assertion library
//        Assertions.assertTrue(i == 2);
//        // is there any benefit of this condition. above is better
//
//        Assertions.assertEquals(i, 2);
//        // this is giving more clarity
//
//        Assertions.assertThrows();
//
//        Assertions.assertNull();
//
//        // matching arrays
//        int[] input = new int[]{4, 1, 9, 0, 8, 3, 2, 5};
//        int[] output = sortThisForMe(input);
//        int[] expectedOutput = new int[]{0, 1, 2, 3, 4, 5, 8, 9};
//
//        for(int i = 0; i < output.length; i++){
//            assert expectedOutput[i] == output[i];
//        }
//
//        Assertions.assertArrayEquals(expectedOutput, output);
//
//        Assertions.assertLinesMatch();
//
//        Assertions.assertTimeout(
//                Duration.of(2L, ChronoUnit.SECONDS),
//                mySortMethod()
//        );
//        // if this mySortMethod() doesnt complete in 2 seconds, this will throw Assertions Exception
//        // can you write the Problem Solver evaluator using this Assertions?
//        // you can check timeout of your Scaler code here.
//
//
//        // how many of you remember your factory method.
//        Assertions.assertInstanceOf(
//                Pigeon.class,
//                BirdFactory.getByName("Pigeon")
//        );
//        // checking if the object I get from factory is of Pigeon class
//
//        // confusion point
//        // comment above code
//        assert x == 3;
//        // does the error explain anything? no. just prints something went wrong
//
//
//        // first run this. is it correct?
//        Assertions.assertEquals(x, 3);
//
//        // You should always give the expectations first, then the computed output!
//        // many people confuse, and write in the opposite way.
//        Assertions.assertEquals(3, x);
//        // first parameter is the value you expect!
//        // what you know, should come first.
//        // second parameter is what your method has computed.
//
//
//        // -----------------------------------------------
//        // have we interacted with spring boot till now?
//        // I want to run the tests, using Spring.
//        // Spring should create the ApplicationContext.
//        // And then run this test case within that context.
//        // add Annotation @SpringBootTest, and run this assertion below
//        Assertions.assertEquals(2, x);
//        // first the complete SpringBoot App is initialized.
//        // then the assertion is run.
//        // Benefit: Now you can Autowire, and add your Autowired dependencies!
//        // add Autowired dependency ProductController above
//
//        // now go to ProductServiceApplicationTests, it has an empty context loads method.
//        // why??
//    }
//}
//
