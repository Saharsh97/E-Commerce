package com.scaler.productservice;

import com.scaler.productservice.models.Product;

import java.util.List;

public class SampleTestClassForList {
    public static void main(String[] args) {
        List<String> l1 = List.of("hello", "there", "ok");
        List<Integer> l2 = List.of(1, 2, 3, 4, 6);
        List l3 = List.of(1, "hello", 10.9, new Product());
        List<Product> l4 = List.of(new Product(), new Product());

        System.out.println(l1.getClass());
        System.out.println(l2.getClass());
        System.out.println(l3.getClass());
        System.out.println(l4.getClass());
    }
}
