package com.scaler.productservice.controllers;

import com.scaler.productservice.dto.ErrorResponseDTO;
import com.scaler.productservice.dto.RequestDTO;
import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
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

    @Autowired
    public ProductController(@Qualifier("selfProductService") IProductService productService){
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts(){
    // hardcoded: 16, 17, 18. my copy: 75, 76, 77
        List<Product> productsFromService = productService.getAllProducts();
        // a: 73, b: 64, c: 75
        List<Product> finalProductsList = new ArrayList<>();
//        finalProductsList.add(productsFromService.get(0));
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
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
        ResponseEntity<Product> responseEntity;
        Product product = productService.getSingleProduct(id);
        responseEntity = new ResponseEntity<>(
                product,
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

    @GetMapping("/category/{catName}")
    public List<Product> getAllProducts(@PathVariable("catName") String categoryName){
        return new ArrayList<>();
    }

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
