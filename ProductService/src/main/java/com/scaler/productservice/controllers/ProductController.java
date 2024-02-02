package com.scaler.productservice.controllers;

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
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
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
        Product product = new Product();
        product.setTitle(requestDTO.getTitle());
        product.setDescription(requestDTO.getDescription());
        product.setImageUrl(requestDTO.getImage());
        product.setPrice(requestDTO.getPrice());
        product.setCategory(new Category());
        product.getCategory().setName(requestDTO.getCategory());

        return productService.addProduct(product);
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody RequestDTO requestDTO) throws ProductNotFoundException {
        // request only contains fields that have to be updated for this id.
        Product product = getProductFromRequestDTO(requestDTO);
        return productService.updateProduct(id, product);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody RequestDTO requestDTO){
        if(requestDTO.getTitle() == null || requestDTO.getDescription() == null || requestDTO.getCategory() == null){
            return null;
        }
        return productService.replaceProduct(id, getProductFromRequestDTO(requestDTO));
    }

    @DeleteMapping("/{id}")
    public boolean deleteProduct(@PathVariable("id") Long id){
        return true;
    }

    private Product getProductFromRequestDTO(RequestDTO requestDTO){
        Product product = new Product();
        product.setTitle(requestDTO.getTitle());
        product.setDescription(requestDTO.getDescription());
        product.setImageUrl(requestDTO.getImage());
        product.setPrice(requestDTO.getPrice());
        product.setCategory(new Category());
        product.getCategory().setName(requestDTO.getCategory());
        return product;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleProductNotFoundException(
            ProductNotFoundException productNotFoundException
    ){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setMessage(productNotFoundException.getMessage() + " (in FakeStoreProductController class)");
        ResponseEntity<ErrorResponseDTO> responseEntity = new ResponseEntity(
                errorResponseDTO,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
        return responseEntity;
    }
}
