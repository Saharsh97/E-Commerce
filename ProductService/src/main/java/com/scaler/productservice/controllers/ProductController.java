package com.scaler.productservice.controllers;

import com.scaler.productservice.dto.RequestDTO;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    IProductService productService;

    @Autowired
    public ProductController(IProductService productService){
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts(){
        ResponseEntity<List<Product>> responseEntity = new ResponseEntity(
                productService.getAllProducts(),
                HttpStatus.NOT_FOUND    // go inside, see all codes.
        );
        // run this. you get response, but code is 404.
        ResponseEntity<List<Product>> responseEntity = new ResponseEntity(
                productService.getAllProducts(),
                HttpStatus.FORBIDDEN    // go inside, see all codes.
        );

        // this is the preferred controller response.
        return responseEntity;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") Long id){
        ResponseEntity responseEntity;
        Product product = productService.getSingleProduct(id);
        responseEntity = new ResponseEntity(
                product,
                HttpStatus.OK
        );
        return responseEntity;
    }

    @GetMapping("/category")
    public List<Category> getAllCategories(){
        return new ArrayList<>();
    }

    @GetMapping("/category/{catName}")
    public List<Product> getAllProducts(@PathVariable("catName") String categoryName){
        return new ArrayList<>();
    }

    @PostMapping()
    public Product addProduct(@RequestBody RequestDTO requestDTO){
        return new Product();
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody RequestDTO requestDTO){
        return new Product();
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody RequestDTO requestDTO){
        if(requestDTO.getTitle() == null || requestDTO.getDescription() == null || requestDTO.getCategory() == null){
            return null;
        }

        return productService.replaceProduct(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    public boolean deleteProduct(@PathVariable("id") Long id){
        return true;
    }
}
