package com.scaler.productservice.services;

import com.scaler.productservice.dto.RequestDTO;
import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Product;

import java.util.List;

public interface IProductService {
    public Product getSingleProduct(Long id) throws ProductNotFoundException;
    public List<Product> getAllProducts();
    public Product replaceProduct(Long id, RequestDTO requestDTO);
}
