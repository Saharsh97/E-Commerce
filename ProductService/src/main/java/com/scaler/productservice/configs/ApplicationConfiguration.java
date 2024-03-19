package com.scaler.productservice.configs;

import com.scaler.productservice.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfiguration {

    @Bean
    @LoadBalanced
    public RestTemplate createRestTemplate(){
        return new RestTemplateBuilder().build();
    }

}
