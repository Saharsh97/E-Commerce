package com.scaler.productservice.services;

import com.scaler.productservice.dto.ResponseDTO;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductService implements IProductService{

    RestTemplate restTemplate;


    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public Product getProductFromResponseDTO(ResponseDTO responseDTO){
        Product product = new Product();
        product.setId(responseDTO.getId());
        product.setName(responseDTO.getTitle());
        product.setDescription(responseDTO.getDescription());
        product.setPrice(responseDTO.getPrice());
        product.setCategory(new Category());
        product.getCategory().setName(responseDTO.getCategory());
        product.setImageUrl(responseDTO.getImage());
        return product;
    }

    @Override
    public Product getSingleProduct(Long id) {
        // hit the FakeStore API, and get the response.
        ResponseDTO responseDTO = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                ResponseDTO.class
        );

        // parse the response, and convert it to Product!
        Product product = getProductFromResponseDTO(responseDTO);
        return product;
    }
}
