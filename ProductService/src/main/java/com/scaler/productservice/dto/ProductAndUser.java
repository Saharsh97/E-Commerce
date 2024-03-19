package com.scaler.productservice.dto;

import com.scaler.productservice.models.Product;
import com.scaler.productservice.models.UserDTO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductAndUser {
    private Product product;
    private UserDTO userDTO;
}
