package com.scaler.productservice.services;

import com.scaler.productservice.models.Product;

public interface IProductService {
    public Product getSingleProduct(Long id);
}
