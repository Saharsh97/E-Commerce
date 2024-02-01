package com.scaler.productservice.inheritanceDemo.joinedTable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity (name = "jt_mentor")
@PrimaryKeyJoinColumn (name = "user_id")
// primary key of this table (mentor), joins on the primary of the parent (user) table.
// This join is on the user_id column of this (mentor) table.
public class Mentor extends User {
    private double averageRating;
}

// Mentor table.
// user_id
// average_rating.

// User
// id   name   email
// 1    awjjd asciejv
// 2    sadijf egjewgo
// 3
// 4


// Mentor
// user_id   average_rating
// 2         4.5
// 5         4.7
// 10        4.9
