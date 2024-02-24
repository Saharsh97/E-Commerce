package com.scaler.productservice.commons;

import com.scaler.productservice.models.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationCommons {


    private RestTemplate restTemplate;

    @Autowired
    public AuthenticationCommons(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public UserDTO validateTokens(String token){
        ResponseEntity<UserDTO> apiResponse = restTemplate.postForEntity(
                "http://localhost:8181/users/validate/" + token,
                null,
                UserDTO.class
        );
        return apiResponse.getBody();
    }
}
