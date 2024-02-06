package com.scaler.productservice.services;

import com.scaler.productservice.dto.RequestDTO;
import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.repositories.CategoryRepository;
import com.scaler.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
@Qualifier("selfProductService")
public class SelfProductService implements IProductService{

    ProductRepository productRepository;
    CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("product with id " + id + " does not exists");
        }
        Product product = productOptional.get();
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product addProduct(Product product) {
        // you get a product, with an existing category
        // or, you get a product, with a new category.
        Optional<Category> categoryOptional = categoryRepository.findByName(product.getCategory().getName());
        if(categoryOptional.isEmpty()){

        } else {
            product.setCategory(categoryOptional.get());
        }
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    @Override
    public Product updateProduct(Long id, Product product) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("product with id " + id + "does not exist. could not update the product");
        }
        Product existingProduct = productOptional.get();

        Product productToBeSaved = new Product();

        productToBeSaved.setTitle(
                product.getTitle() != null ?
                        product.getTitle() :
                        existingProduct.getTitle()
        );

        productToBeSaved.setDescription(
                product.getDescription() != null ?
                        product.getDescription() :
                        existingProduct.getDescription()
        );

        productToBeSaved.setPrice(
                product.getPrice() > 0 ?
                        product.getPrice():
                        existingProduct.getPrice()
        );

        productToBeSaved.setImageUrl(
                product.getImageUrl() != null ?
                        product.getImageUrl() :
                        existingProduct.getImageUrl()
        );

        if(product.getCategory().getName() != null){
            Optional<Category> categoryOptional = categoryRepository.findByName(product.getCategory().getName());
            Category savedCategory;
            if(categoryOptional.isEmpty()){
                Category category = new Category();
                category.setName(product.getCategory().getName());
                savedCategory = categoryRepository.save(category);
            } else {
                savedCategory = categoryOptional.get();
            }
            productToBeSaved.setCategory(savedCategory);
        } else {
            productToBeSaved.setCategory(existingProduct.getCategory());
        }

        productToBeSaved.setId(id);

        return productRepository.save(productToBeSaved);
    }

    @Override
    public Product replaceProduct(Long id, RequestDTO requestDTO) {
        return null;
    }
}
