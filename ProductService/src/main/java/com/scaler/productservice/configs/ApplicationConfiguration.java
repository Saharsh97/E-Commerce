package com.scaler.productservice.configs;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration // with and without this, error in another class.
// will it work if its @Controller or @Service, yes.

public class ApplicationConfiguration { // just a class. could be different name.

    @Bean
    public RestTemplate createRestTemplate(){
        return new RestTemplateBuilder().build();
    }
}
