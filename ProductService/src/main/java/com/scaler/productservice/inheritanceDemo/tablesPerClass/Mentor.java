package com.scaler.productservice.inheritanceDemo.tablesPerClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tpc_mentor")
// do i need to put inheritance here? no. only at the parent class.
public class Mentor extends User {
    private double averageRating;
}
