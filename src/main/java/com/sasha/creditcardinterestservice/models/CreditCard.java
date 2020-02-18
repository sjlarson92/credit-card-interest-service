package com.sasha.creditcardinterestservice.models;

public class CreditCard {
    int id;
    Type type;
    int balance;

    public CreditCard() {}

    public CreditCard(
            int id,
            Type type,
            int balance
    ) {
        this.id = id;
        this.type = type;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public int getBalance() {
        return balance;
    }

    public enum Type {
        VISA, MASTERCARD, DISCOVER
    }
}
