package com.scaler.productservice.services;

import com.scaler.productservice.dto.FakeStoreProductDTO;
import com.scaler.productservice.dto.ErrorResponseDTO;
import com.scaler.productservice.dto.RequestDTO;
import com.scaler.productservice.dto.ResponseDTO;
import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
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


    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public Product getProductFromResponseDTO(FakeStoreProductDTO fakeStoreProductDTO){
        Product product = new Product();
        product.setId(responseDTO.getId());
        product.setTitle(responseDTO.getTitle());
        product.setDescription(responseDTO.getDescription());
        product.setPrice(responseDTO.getPrice());
        product.setCategory(new Category());
        product.getCategory().setName(fakeStoreProductDTO.getCategory());
        product.setImageUrl(fakeStoreProductDTO.getImage());
        return product;
    }

    @Override
    public Product getSingleProduct(Long id) {
        int a = 1/0;    // all RunTime exceptions cannot be caught.
    public Product getSingleProduct(Long id) throws ProductNotFoundException {
        // hit the FakeStore API, and get the response.
        FakeStoreProductDTO fakeStoreProductDTO = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDTO.class
        );

        if(responseDTO == null){
            throw new ProductNotFoundException("product with id " + id + " does not exist");
        }

        // parse the response, and convert it to Product!
        Product product = getProductFromResponseDTO(fakeStoreProductDTO);
        return product;
    }

    @Override
    public List<Product> getAllProducts(){
        List<FakeStoreProductDTO> response = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                List<FakeStoreProductDTO>.class // explain error here.
                // java not allowing to convert anything into list of products, or even objects.
                // 1. notes.
        );
        // at runtime, Java does not know what is the datatype inside list.
        // runtime only knows I have to convert to list. but list of what??
        List<FakeStoreProductDTO> response = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                List.class
        );
        // run in debug mode upto this point
        // tries to convert json into hashmap
        // you had given the required type as List of FakeStoreProductDTO, but got list of HashMaps.
        // why? because java doesnt know the data type at RunTime.

        // what can we do then? Something which is similar to List? Array.
        // use FakeStoreProductDTO[].class
        FakeStoreProductDTO[] response = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreProductDTO[].class
                // solving is easy, but knowing the root cause of problem is much more beautiful
                // this happened because of type erasure!
        );
        List<Product> answer = new ArrayList<>();

        for(FakeStoreProductDTO dto: response){
            answer.add(getProductFromResponseDTO(dto));
        }
        return answer;
    }


    @Override
    public Product addProduct(Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

//    @Override
//    public Product replaceProduct(Long id, Product product){
//
//        RequestCallback requestCallback =
//                restTemplate.httpEntityCallback(requestDTO, ResponseDTO.class);
//
//        HttpMessageConverterExtractor<ResponseDTO> responseExtractor =
//                new HttpMessageConverterExtractor(
//                        ResponseDTO.class,
//                        restTemplate.getMessageConverters()
//                );
//
//        // in 1 single call, I am able to PUT an object,
//        // as well as, get the same object in response.
//        ResponseDTO responseDTO = restTemplate.execute(
//                "https://fakestoreapi.com/products/" + id,
//                HttpMethod.PUT,
//                requestCallback,
//                responseExtractor
//        );
//
//        return getProductFromResponseDTO(responseDTO);
//    }
}
