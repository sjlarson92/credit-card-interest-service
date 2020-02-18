package com.sasha.creditcardinterestservice.models;

import java.math.BigDecimal;
import java.util.Map;

public class CustomerAndCreditCardInterest {
    int id;
    String firstName;
    String lastName;
    BigDecimal totalInterest;
    Map<Integer, BigDecimal> interestByCreditCard;

    public CustomerAndCreditCardInterest() {}

    public CustomerAndCreditCardInterest(
            int id,
            String firstName,
            String lastName,
            BigDecimal totalInterest,
            Map<Integer, BigDecimal> interestByCreditCard) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalInterest = totalInterest;
        this.interestByCreditCard = interestByCreditCard;
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

    public Map<Integer, BigDecimal> getInterestByCreditCard() {
        return interestByCreditCard;
    }
}
