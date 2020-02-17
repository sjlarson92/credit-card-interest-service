package com.sasha.creditcardinterestservice.models;

import java.util.ArrayList;

public class Wallet {
    int id;
    ArrayList<CreditCard> creditCards;

    public Wallet() {}

    public Wallet (
            int id,
            ArrayList<CreditCard> creditCards
            ) {
        this.id = id;
        this.creditCards = creditCards;
    }

    public int getId() {
        return id;
    }

    public ArrayList<CreditCard> getCreditCards() {
        return creditCards;
    }
}
