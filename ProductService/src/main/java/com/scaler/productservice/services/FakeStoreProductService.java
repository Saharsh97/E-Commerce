package com.scaler.productservice.services;

import com.scaler.productservice.dto.RequestDTO;
import com.scaler.productservice.dto.ResponseDTO;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements IProductService{

    RestTemplate restTemplate;


    @Autowired
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

    @Override
    public List<Product> getAllProducts(){
        ResponseDTO[] responseList = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                ResponseDTO[].class
        );

        List<Product> products = new ArrayList<>();
        for(ResponseDTO responseDTO: responseList){
            products.add(getProductFromResponseDTO(responseDTO));
        }
        return products;
    }

    @Override
    public Product replaceProduct(Long id, RequestDTO requestDTO){

        RequestCallback requestCallback =
                restTemplate.acceptHeaderRequestCallback(ResponseDTO.class);

        HttpMessageConverterExtractor<ResponseDTO> responseExtractor =
                new HttpMessageConverterExtractor(
                        ResponseDTO.class,
                        restTemplate.getMessageConverters()
                );

        // in 1 single call, I am able to PUT an object,
        // as well as, get the same object in response.
        ResponseDTO responseDTO = restTemplate.execute(
                "https://fakestoreapi.com/products/" + id,
                HttpMethod.PUT,
                requestCallback,
                responseExtractor
        );


        return getProductFromResponseDTO(responseDTO);
    }
}
