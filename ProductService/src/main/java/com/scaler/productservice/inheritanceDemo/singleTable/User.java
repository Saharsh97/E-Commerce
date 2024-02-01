package com.scaler.productservice.inheritanceDemo.singleTable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "user_type",
        discriminatorType = DiscriminatorType.INTEGER
)
@DiscriminatorValue("0")
@Entity(name = "st_user")
public class User {
    @Id
    private Long id;

    private String name;
    private String email;
}
