package com.scaler.productservice.services;

import com.scaler.productservice.dto.FakeStoreProductDTO;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductService implements IProductService{
    RestTemplate restTemplate;

    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public Product convertDTOtoProduct(FakeStoreProductDTO productDTO){
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getTitle());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(new Category());
        product.getCategory().setName(productDTO.getCategory());
        product.setImageUrl(productDTO.getImage());
        return product;
    }

    @Override
    public Product getSingleProduct(Long id) {
        FakeStoreProductDTO productDTO = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDTO.class   // one on one mapping of json response with this class.
        );
        // convert from dto to Product object.
        Product product = convertDTOtoProduct(productDTO);
        return product;
    }
}
