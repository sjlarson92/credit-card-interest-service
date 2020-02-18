package com.sasha.creditcardinterestservice.models;

import java.math.BigDecimal;
import java.util.Map;

public class CustomerAndWalletInterest {
    int id;
    String firstName;
    String lastName;
    BigDecimal totalInterest;
    Map<Integer, BigDecimal> interestByWallet;

    public CustomerAndWalletInterest() {}

    public CustomerAndWalletInterest(
            int id,
            String firstName,
            String lastName,
            BigDecimal totalInterest,
            Map<Integer, BigDecimal> interestByWallet) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalInterest = totalInterest;
        this.interestByWallet = interestByWallet;
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

    public Map<Integer, BigDecimal> getInterestByWallet() {
        return interestByWallet;
    }


}
