package com.scaler.productservice.inheritanceDemo.singleTable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "st_users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "userType",
        discriminatorType = DiscriminatorType.INTEGER
)
@DiscriminatorValue("0")
// default value. if only user, and none of userTypes
public class User {
    @Id
    private Long id;
    private String name;
    private String email;
}
