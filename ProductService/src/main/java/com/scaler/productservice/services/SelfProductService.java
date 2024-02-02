package com.scaler.productservice.services;

import com.scaler.productservice.dto.RequestDTO;
import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.repositories.CategoryRepository;
import com.scaler.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service    // show error when two @Service
@Qualifier("selfProductService")
public class SelfProductService implements IProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException {
        // you can check product == null. but what if you forget to?
        // in case of optional, you have to check for presence.
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
        // show error here, by just running save().
//        return productRepository.save(product);

        // need to first get category information.
        // then save the complete product.
        // how to debug errors, read the log! (from the beginning)
        // error is happening because i am calling a save method. persist().
        // inorder to save, I have to tell, how to generate the id!
        Optional<Category> categoryOptional =
                categoryRepository.findByName(product.getCategory().getName());

        Category savedCategory = new Category();
        if(categoryOptional.isEmpty()){
            Category categoryToSave = new Category();
            categoryToSave.setName(product.getCategory().getName());
            savedCategory = categoryRepository.save(categoryToSave);
        } else {
            savedCategory = categoryOptional.get();
        }
        product.setCategory(savedCategory);

        return productRepository.save(product);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) throws ProductNotFoundException {
        // first, get the existing product for id.
        Optional<Product> existingProductOptional = productRepository.findById(id);
        if(existingProductOptional.isEmpty()){
            throw new ProductNotFoundException("product with id " + id + " does not exist. Cannot perform update");
        }
        Product existingProduct = existingProductOptional.get();

        Product updatedProduct = new Product();

        updatedProduct.setId(id);

        updatedProduct.setTitle(
                product.getTitle() != null ?
                        product.getTitle() :
                        existingProduct.getTitle()
        );

        updatedProduct.setDescription(
                product.getDescription() != null ?
                        product.getDescription() :
                        existingProduct.getDescription()
        );

        updatedProduct.setPrice(
                product.getPrice() > 0 ?
                        product.getPrice() :
                        existingProduct.getPrice()
        );

        updatedProduct.setImageUrl(
                product.getImageUrl() != null ?
                        product.getImageUrl() :
                        existingProduct.getImageUrl()
        );

        if(product.getCategory().getName() != null) {
            Category savedCategory = getSavedCategoryByName(product.getCategory().getName());
            updatedProduct.setCategory(savedCategory);
        } else {
            updatedProduct.setCategory(existingProduct.getCategory());
        }

        return productRepository.save(updatedProduct);
    }

    // HW. Implement remaining 4 APIs.

    private Category getSavedCategoryByName(String categoryName){
        Optional<Category> categoryOptional = categoryRepository.findByName(categoryName);
        Category savedCategory = new Category();
        if (categoryOptional.isEmpty()) {
            Category categoryToSave = new Category();
            categoryToSave.setName(categoryName);
            savedCategory = categoryRepository.save(categoryToSave);
        } else {
            savedCategory = categoryOptional.get();
        }
        return savedCategory;
    }
}
