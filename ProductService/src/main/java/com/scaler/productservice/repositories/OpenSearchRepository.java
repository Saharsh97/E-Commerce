package com.scaler.productservice.repositories;

import com.scaler.productservice.models.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface OpenSearchRepository extends ElasticsearchRepository<Product, Long> {
    Optional<Product> findByTitle(String title);
}
