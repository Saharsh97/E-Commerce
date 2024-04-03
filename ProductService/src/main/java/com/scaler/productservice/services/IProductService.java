package com.scaler.productservice.services;

import com.scaler.productservice.dto.ProductAndUser;
import com.scaler.productservice.dto.RequestDTO;
import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Product;

import java.util.List;

public interface IProductService {
    public ProductAndUser getSingleProduct(Long id) throws Exception;
    public List<Product> getAllProducts();
    public Product addProduct(Product product);
    public Product updateProduct(Long id, Product product) throws ProductNotFoundException;
    public Product replaceProduct(Long id, Product product);
}
