package com.scaler.productservice;

import com.scaler.productservice.models.Product;

import java.util.ArrayList;
import java.util.List;

public class SampleLists {
    public static void main(String[] args) {
        List<Integer> l1 = List.of(1, 2, 3, 4);
        List<Object> l2 = List.of(new Object(), new Object());
        List<String> l3 = List.of("hello", "how", "are", "you");
        List<Product> l4 = List.of(new Product(), new Product());
        List<Object> l5 = List.of(new Object(), new Object());

        System.out.println(l1.getClass());
        System.out.println(l2.getClass());
        System.out.println(l3.getClass());
        System.out.println(l4.getClass());
        System.out.println(l5.getClass());
    }
}
