package com.sasha.creditcardinterestservice.models;

import java.math.BigDecimal;

public class CustomerInterest {
    int id;
    String firstName;
    String lastName;
    BigDecimal totalInterest;

    public CustomerInterest() {}

    public CustomerInterest(int id, String firstName, String lastName, BigDecimal totalInterest) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalInterest = totalInterest;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public BigDecimal getTotalInterest() {
        return totalInterest;
    }
}
