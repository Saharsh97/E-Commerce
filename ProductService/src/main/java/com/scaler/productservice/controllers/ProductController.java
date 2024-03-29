package com.scaler.productservice.controllers;

import com.scaler.productservice.commons.AuthenticationCommons;
import com.scaler.productservice.dto.ErrorResponseDTO;
import com.scaler.productservice.dto.ProductAndUser;
import com.scaler.productservice.dto.RequestDTO;
import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.models.Role;
import com.scaler.productservice.models.UserDTO;
import com.scaler.productservice.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    IProductService productService;

    AuthenticationCommons authenticationCommons;


    @Autowired
    public ProductController(@Qualifier("selfProductService") IProductService productService, AuthenticationCommons authenticationCommons){
        this.productService = productService;
        this.authenticationCommons = authenticationCommons;
    }

    // lets say, you hit UserService to validate the token
    // lets say, you get a null response: request is rejected by Product Service
    // you get a UserDTO object, but it does not have the correct roles.
    // then again ProductService will reject the request
    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts(){

        List<Product> productsFromService = productService.getAllProducts();

        List<Product> finalProductsList = new ArrayList<>();
        for(Product product: productsFromService){
            product.setTitle("Hello " + product.getTitle());
            finalProductsList.add(product);
        }

        ResponseEntity responseEntity = new ResponseEntity(
                finalProductsList,
                HttpStatus.OK
        );
        return responseEntity;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductAndUser> getSingleProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
        System.out.println("got request in ProductController");
        ResponseEntity<ProductAndUser> responseEntity;
        ProductAndUser productAndUser = productService.getSingleProduct(id);
        responseEntity = new ResponseEntity<>(
                productAndUser,
                HttpStatus.OK
        );
        return responseEntity;
    }

    @GetMapping("/category")
    public ResponseEntity<List<Category>> getAllCategories(){
        ResponseEntity responseEntity = new ResponseEntity(
                new ArrayList<>(),
                HttpStatus.ACCEPTED
        );
        return responseEntity;
    }

//    @GetMapping("/category/{catName}")
//    public List<Product> getAllProducts(@PathVariable("catName") String categoryName){
//        return new ArrayList<>();
//    }

    @PostMapping()
    public Product addProduct(@RequestBody RequestDTO requestDTO){
        Product product = convertRequestDTOToProduct(requestDTO);

        Product savedProduct = productService.addProduct(product);
        return savedProduct;
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody RequestDTO requestDTO) throws ProductNotFoundException {
        Product product = convertRequestDTOToProduct(requestDTO);

        return productService.updateProduct(id, product);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody RequestDTO requestDTO){
        if(requestDTO.getTitle() == null || requestDTO.getDescription() == null || requestDTO.getCategory() == null){
            return null;
        }
        Product product = convertRequestDTOToProduct(requestDTO);

        return productService.replaceProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public boolean deleteProduct(@PathVariable("id") Long id){
        return true;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleProductNotFoundException(
            ProductNotFoundException productNotFoundException
    ){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setMessage(productNotFoundException.getMessage() + " (response generated at Controller Level)");
        ResponseEntity<ErrorResponseDTO> responseEntity = new ResponseEntity(
                errorResponseDTO,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
        return responseEntity;
    }

    private Product convertRequestDTOToProduct(RequestDTO requestDTO){
        Product product = new Product();
        product.setTitle(requestDTO.getTitle());
        product.setDescription(requestDTO.getDescription());
        product.setPrice(requestDTO.getPrice());
        product.setPicture(requestDTO.getImage());

        product.setCategory(new Category());
        product.getCategory().setName(requestDTO.getCategory());
        return product;
    }
}
