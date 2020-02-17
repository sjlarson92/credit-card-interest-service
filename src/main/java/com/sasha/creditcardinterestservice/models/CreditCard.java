package com.sasha.creditcardinterestservice.models;

public class CreditCard {
    int id;
    CreditCardType type;
    int balance;

    public CreditCard() {}

    public CreditCard(
            int id,
            CreditCardType type,
            int balance
    ) {
        this.id = id;
        this.type = type;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public CreditCardType getType() {
        return type;
    }

    public int getBalance() {
        return balance;
    }

    private enum CreditCardType {
        VISA, MASTERCARD, DISCOVER
    }
}
