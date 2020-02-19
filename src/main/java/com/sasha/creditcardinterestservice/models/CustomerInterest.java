package com.sasha.creditcardinterestservice.models;

import java.math.BigDecimal;
import java.util.Map;

public class CustomerInterest {
    int customerId;
    String firstName;
    String lastName;
    BigDecimal totalInterest;
    Map<String, Map<Integer, BigDecimal>> interestByType;

    public CustomerInterest() {}

    public CustomerInterest(
            int customerId,
            String firstName,
            String lastName,
            BigDecimal totalInterest,
            Map<String, Map<Integer, BigDecimal>> interestByType) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalInterest = totalInterest;
        this.interestByType = interestByType;
    }

    public int getCustomerId() {
        return customerId;
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

    public Map<String, Map<Integer, BigDecimal>> getInterestByType() {
        return interestByType;
    }
}
