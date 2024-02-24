package com.scaler.productservice.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {
    private Long userId;
    private String name;
    private String email;
    private List<Role> roles;
}


