package com.scaler.productservice.services;

import com.scaler.productservice.dto.*;
import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
//import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
@Qualifier("fakeStoreProductService")
public class FakeStoreProductService implements IProductService{

    RestTemplate restTemplate;
//    RedisTemplate<String, Product> redisTemplate;

    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
//        this.redisTemplate = redisTemplate;
    }

    public Product getProductFromResponseDTO(ResponseDTO responseDTO){
        Product product = new Product();
        product.setId(responseDTO.getId());
        product.setTitle(responseDTO.getTitle());
        product.setDescription(responseDTO.getDescription());
        product.setPrice(responseDTO.getPrice());
        product.setCategory(new Category());
        product.getCategory().setName(responseDTO.getCategory());
//        product.getPicture(responseDTO.getImage());
        return product;
    }

//    @Override
//    public Product getSingleProduct(Long id) throws ProductNotFoundException{
//
//        // I want to run an operation on hash value
////        Product p = (Product) redisTemplate.opsForHash().get("PRODUCTS", "PRODUCT_" + id);
////        if(p != null){
////            return p;
////        }
//
//        ResponseDTO responseDTO = restTemplate.getForObject(
//                "https://fakestoreapi.com/products/" + id,
//                ResponseDTO.class
//        );
//
//        if(responseDTO == null){
//            throw new ProductNotFoundException("product with id " + id + " does not exists");
//        }
//
//        Product product = getProductFromResponseDTO(responseDTO);
//
////        redisTemplate.opsForHash().put("PRODUCTS", "PRODUCT_"+id, product);
//
//        return product;
//    }

    @Override
    public ProductAndUser getSingleProduct(Long id) throws ProductNotFoundException {
        return null;
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
    public Product addProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product){

        FakeStoreRequestDTO requestDTO = new FakeStoreRequestDTO();
        requestDTO.setTitle(product.getTitle());
        requestDTO.setDescription(product.getDescription());
        requestDTO.setPrice(product.getPrice());
        requestDTO.setCategory(product.getCategory().getName());
        requestDTO.setImage(product.getPicture());

        RequestCallback requestCallback =
                restTemplate.httpEntityCallback(requestDTO, ResponseDTO.class);

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
