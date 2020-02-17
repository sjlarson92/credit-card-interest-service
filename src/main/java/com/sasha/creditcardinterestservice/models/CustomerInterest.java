package com.sasha.creditcardinterestservice.models;

public class CustomerInterest {
    int id;
    String firstName;
    String lastName;
    int totalInterest;

    public CustomerInterest() {}

    public CustomerInterest(int id, String firstName, String lastName, int totalInterest) {
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

    public int getTotalInterest() {
        return totalInterest;
    }
}
