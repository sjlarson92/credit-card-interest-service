package com.sasha.creditcardinterestservice.models;

import java.util.ArrayList;

public class Customer {
    int id;
    String firstName;
    String lastName;
    ArrayList<Wallet> wallets;

    public Customer() {}

    public Customer(
            int id,
            String firstName,
            String lastName,
            ArrayList<Wallet> wallets
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.wallets = wallets;
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

    public ArrayList<Wallet> getWallets() {
        return wallets;
    }
}
