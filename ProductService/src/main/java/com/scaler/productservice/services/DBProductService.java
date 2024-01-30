package com.scaler.productservice.services;

import com.scaler.productservice.dto.RequestDTO;
import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBProductService implements IProductService{
    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, RequestDTO requestDTO) {
        return null;
    }
}
