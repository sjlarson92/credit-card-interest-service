package com.sasha.creditcardinterestservice.services;

import com.sasha.creditcardinterestservice.models.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InterestService {

    public ArrayList<CustomerAndCreditCardInterest> getCustomerAndCreditCardInterest(ArrayList<Customer> customers) {
        ArrayList<CustomerAndCreditCardInterest> customerAndCreditCardInterestArray = new ArrayList<>();
        customers.forEach(customer -> {

            BigDecimal totalInterest = getCustomerTotalInterest(customer);
            Map<Integer, BigDecimal> interestByCreditCard = getInterestByCreditCardId(customer);

            CustomerAndCreditCardInterest customerAndCreditCardInterest = new CustomerAndCreditCardInterest(
                    customer.getId(),
                    customer.getFirstName(),
                    customer.getLastName(),
                    totalInterest,
                    interestByCreditCard
            );
            customerAndCreditCardInterestArray.add(customerAndCreditCardInterest);
        });


        return customerAndCreditCardInterestArray;
    }

    public ArrayList<CustomerAndWalletInterest> getCustomerAndWalletInterest(ArrayList<Customer> customers) {
        ArrayList<CustomerAndWalletInterest> customerAndWalletInterestArray = new ArrayList<>();
        customers.forEach(customer -> {

            BigDecimal totalInterest = getCustomerTotalInterest(customer);
            Map<Integer, BigDecimal> interestByWallet = getInterestByWalletId(customer);

            CustomerAndWalletInterest customerAndWalletInterest = new CustomerAndWalletInterest(
                    customer.getId(),
                    customer.getFirstName(),
                    customer.getLastName(),
                    totalInterest,
                    interestByWallet
            );
            customerAndWalletInterestArray.add(customerAndWalletInterest);
        });


        return customerAndWalletInterestArray;
    }

    public BigDecimal getCustomerTotalInterest(Customer customer) {
        BigDecimal totalInterest = BigDecimal.ZERO;
        ArrayList<Wallet> wallets = customer.getWallets();

        for(Wallet wallet: wallets) {
            ArrayList<CreditCard> creditCards = wallet.getCreditCards();
            for(CreditCard creditCard : creditCards) {

                int creditCardBalance = creditCard.getBalance();
                CreditCard.Type creditCardType = creditCard.getType();

                BigDecimal creditCardInterest = BigDecimal.valueOf(0);
                switch (creditCardType) {
                    case VISA:
                        creditCardInterest = BigDecimal.valueOf(creditCardBalance * .1).setScale(2, RoundingMode.HALF_UP);
                        break;
                    case DISCOVER:
                        creditCardInterest = BigDecimal.valueOf(creditCardBalance * .01).setScale(2, RoundingMode.HALF_UP);
                        break;
                    case MASTERCARD:
                        creditCardInterest = BigDecimal.valueOf(creditCardBalance * .05).setScale(2, RoundingMode.HALF_UP);
                        break;
                }
                totalInterest =  totalInterest.add(creditCardInterest);
            }
        }
        return totalInterest;
    }

    public Map<Integer, BigDecimal> getInterestByWalletId(Customer customer) {
        Map<Integer, BigDecimal> interestByWallet = new HashMap<>();

        ArrayList<Wallet> wallets = customer.getWallets();

        for(Wallet wallet: wallets) {
            BigDecimal walletInterest = BigDecimal.valueOf(0);
            ArrayList<CreditCard> creditCards = wallet.getCreditCards();
            for(CreditCard creditCard : creditCards) {

                int creditCardBalance = creditCard.getBalance();
                CreditCard.Type creditCardType = creditCard.getType();

                BigDecimal creditCardInterest = BigDecimal.valueOf(0);
                switch (creditCardType) {
                    case VISA:
                        creditCardInterest = BigDecimal.valueOf(creditCardBalance * .1).setScale(2, RoundingMode.HALF_UP);
                        break;
                    case DISCOVER:
                        creditCardInterest = BigDecimal.valueOf(creditCardBalance * .01).setScale(2, RoundingMode.HALF_UP);
                        break;
                    case MASTERCARD:
                        creditCardInterest = BigDecimal.valueOf(creditCardBalance * .05).setScale(2, RoundingMode.HALF_UP);
                        break;
                }
                walletInterest = walletInterest.add(creditCardInterest);
            }
            interestByWallet.put(wallet.getId(), walletInterest);
        }
        return interestByWallet;
    }

    public Map<Integer, BigDecimal> getInterestByCreditCardId(Customer customer) {
        ArrayList<Wallet> wallets = customer.getWallets();
        Map<Integer, BigDecimal> interestByCreditCard = new HashMap<>();
        for(Wallet wallet: wallets) {
            ArrayList<CreditCard> creditCards = wallet.getCreditCards();
            for(CreditCard creditCard : creditCards) {

                int creditCardBalance = creditCard.getBalance();
                CreditCard.Type creditCardType = creditCard.getType();

                BigDecimal creditCardInterest = BigDecimal.valueOf(0);
                switch (creditCardType) {
                    case VISA:
                        creditCardInterest = BigDecimal.valueOf(creditCardBalance * .1).setScale(2, RoundingMode.HALF_UP);
                        break;
                    case DISCOVER:
                        creditCardInterest = BigDecimal.valueOf(creditCardBalance * .01).setScale(2, RoundingMode.HALF_UP);
                        break;
                    case MASTERCARD:
                        creditCardInterest = BigDecimal.valueOf(creditCardBalance * .05).setScale(2, RoundingMode.HALF_UP);
                        break;
                }
                interestByCreditCard.put(creditCard.getId(), creditCardInterest);
            }
        }
        return interestByCreditCard;
    }
}