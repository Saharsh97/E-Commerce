package com.scaler.productservice.services;

import com.scaler.productservice.dto.FakeStoreProductDTO;
import com.scaler.productservice.dto.RequestDTO;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
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


    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public Product getProductFromResponseDTO(FakeStoreProductDTO fakeStoreProductDTO){
        Product product = new Product();
        product.setId(fakeStoreProductDTO.getId());
        product.setName(fakeStoreProductDTO.getTitle());
        product.setDescription(fakeStoreProductDTO.getDescription());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setCategory(new Category());
        product.getCategory().setName(fakeStoreProductDTO.getCategory());
        product.setImageUrl(fakeStoreProductDTO.getImage());
        return product;
    }

    @Override
    public Product getSingleProduct(Long id) {
        int a = 1/0;    // all RunTime exceptions cannot be caught.
        // hit the FakeStore API, and get the response.
        FakeStoreProductDTO fakeStoreProductDTO = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDTO.class
        );

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
    public Product replaceProduct(Long id, RequestDTO requestDTO){
        // in FakeStore, you get back the updated product.
        // but in restTemplate, the return type of put() is? void.
        // what do we need from the put method? we need a response.
        // will the put() method as it is, of restTemplate work for us? no.

        // how to get the updated product?
        // 1. make a get call. problems?
        // you make 2 calls. not efficient.
        // 2. if somehow, put itself gave me the object.

        // when we call restTemplate.put("fakestore/products/{id}")
        // fake store return the json object.
        // but restTemplate just ignored the json body! => void.

        // check implementation of getForObject. it uses execute.
        // check implementation of postForObject. it uses execute.
        // what is more generic? post method or execute? execute!
        // you can just use execute instead. and pass everything that need to be done.
        // execute method is public. it can be used for any kind of request.
        // so why dont we only execute, for all get(), post() etc? Convenience and clarity!
        // execute takes a lot of parameters, and most are not required.
        // you use get() with few parameters.
        // get() inside will call the remaining parameters for execute.
        // 2) notes.
//        new org.springframework.http.HttpMethod("Saharsh");


        RequestCallback requestCallback =
                restTemplate.httpEntityCallback(
                        RequestDTO.class,
                        FakeStoreProductDTO.class);

        HttpMessageConverterExtractor<FakeStoreProductDTO> responseExtractor =
                new HttpMessageConverterExtractor<>(
                        FakeStoreProductDTO.class,
                        restTemplate.getMessageConverters());

        FakeStoreProductDTO apiResponse = restTemplate.execute(
                "https://fakestoreapi.com/products/" + id,
                HttpMethod.PUT,
                requestCallback,
                responseExtractor);

        return getProductFromResponseDTO(apiResponse);
    }
}
