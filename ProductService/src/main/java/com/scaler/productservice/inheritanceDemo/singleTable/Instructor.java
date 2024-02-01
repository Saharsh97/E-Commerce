package com.scaler.productservice.inheritanceDemo.singleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
// just ensures that this table attributes are there.
// does not create a new table!
@DiscriminatorValue("1")
public class Instructor extends User {
    private String company;
}
