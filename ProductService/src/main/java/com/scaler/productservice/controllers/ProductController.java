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
    // ideal logic is, controller returns the same number of products, that the service does.
    // what if, anyone changes the behaviour?
    // code below
    public ResponseEntity<List<Product>> getAllProducts(){
        // will this call the real product service? no.
        // it calls the mock. gets 3 products in response.
        List<Product> products = productService.getAllProducts();

        List<Product> finalProducts = new ArrayList<>();
        // t1
//        finalProducts.add(products.get(0));

        // t2
        for(Product product: products){
            product.setTitle("Hello" + product.getTitle());
            finalProducts.add(product);
        }
        // will this t2 pass?? YES.
        // how many think this will pass?
        // take a b c example, pass by reference.
        // This is a BUG in your TESTCASE
        // USE DEBUGGER
        // create a copy of products to pass, as it can be mutated in the controller execution.
        // you have mutated the list present in the testcase.
        // so its being called wrongly in the test case!
        // be careful of pass by value, pass by reference etc..
        // use Mocking carefully!
        ResponseEntity responseEntity = new ResponseEntity(
                finalProducts,
                HttpStatus.OK
        );
        // will the test case pass?
        // run test case t1
        return responseEntity;
    }

    // t3
    // we want to test, what if product does not exist, I should get exception.
    // add test in ProductControllerTest
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
