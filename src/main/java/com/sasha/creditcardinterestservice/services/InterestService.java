package com.sasha.creditcardinterestservice.services;

import com.sasha.creditcardinterestservice.models.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InterestService {

    public ArrayList<CustomerInterest> getCustomerAndCreditCardInterest(ArrayList<Customer> customers) {
        ArrayList<CustomerInterest> customerInterestArray = new ArrayList<>();
        customers.forEach(customer -> {
            BigDecimal totalInterest = getCustomerTotalInterest(customer);
            Map<Integer, BigDecimal> interestByCreditCard = getInterestByCreditCardId(customer);
            Map<String, Map<Integer, BigDecimal>> interestByType = new HashMap<>();
            interestByType.put("creditCard", interestByCreditCard);
            CustomerInterest customerInterest = new CustomerInterest(
                    customer.getId(),
                    customer.getFirstName(),
                    customer.getLastName(),
                    totalInterest,
                    interestByType
            );
            customerInterestArray.add(customerInterest);
        });

        return customerInterestArray;
    }

    public ArrayList<CustomerInterest> getCustomerAndWalletInterest(ArrayList<Customer> customers) {
        ArrayList<CustomerInterest> customerAndWalletInterestArray = new ArrayList<>();
        customers.forEach(customer -> {
            BigDecimal totalInterest = getCustomerTotalInterest(customer);
            Map<Integer, BigDecimal> interestByWallet = getInterestByWalletId(customer);
            Map<String, Map<Integer, BigDecimal>> interestByType = new HashMap<>();
            interestByType.put("wallet", interestByWallet);
            CustomerInterest customerInterest = new CustomerInterest(
                    customer.getId(),
                    customer.getFirstName(),
                    customer.getLastName(),
                    totalInterest,
                    interestByType
            );
            customerAndWalletInterestArray.add(customerInterest);
        });

        return customerAndWalletInterestArray;
    }

    private BigDecimal getCustomerTotalInterest(Customer customer) {
        BigDecimal totalInterest = BigDecimal.ZERO;
        ArrayList<Wallet> wallets = customer.getWallets();

        for(Wallet wallet: wallets) {
            ArrayList<CreditCard> creditCards = wallet.getCreditCards();
            for(CreditCard creditCard : creditCards) {
                int creditCardBalance = creditCard.getBalance();
                CreditCard.Type creditCardType = creditCard.getType();
                BigDecimal creditCardInterest = getCreditCardInterest(creditCardBalance, creditCardType);
                totalInterest =  totalInterest.add(creditCardInterest);
            }
        }
        return totalInterest;
    }

    private Map<Integer, BigDecimal> getInterestByWalletId(Customer customer) {
        Map<Integer, BigDecimal> interestByWallet = new HashMap<>();

        ArrayList<Wallet> wallets = customer.getWallets();

        for(Wallet wallet: wallets) {
            BigDecimal walletInterest = BigDecimal.valueOf(0);
            ArrayList<CreditCard> creditCards = wallet.getCreditCards();
            for(CreditCard creditCard : creditCards) {
                int creditCardBalance = creditCard.getBalance();
                CreditCard.Type creditCardType = creditCard.getType();
                BigDecimal creditCardInterest = getCreditCardInterest(creditCardBalance, creditCardType);
                walletInterest = walletInterest.add(creditCardInterest);
            }
            interestByWallet.put(wallet.getId(), walletInterest);
        }
        return interestByWallet;
    }

    private Map<Integer, BigDecimal> getInterestByCreditCardId(Customer customer) {
        ArrayList<Wallet> wallets = customer.getWallets();
        Map<Integer, BigDecimal> interestByCreditCard = new HashMap<>();
        for(Wallet wallet: wallets) {
            ArrayList<CreditCard> creditCards = wallet.getCreditCards();
            for(CreditCard creditCard : creditCards) {
                int creditCardBalance = creditCard.getBalance();
                CreditCard.Type creditCardType = creditCard.getType();
                BigDecimal creditCardInterest = getCreditCardInterest(creditCardBalance, creditCardType);
                interestByCreditCard.put(creditCard.getId(), creditCardInterest);
            }
        }
        return interestByCreditCard;
    }

    public BigDecimal getCreditCardInterest(int creditCardBalance, CreditCard.Type creditCardType) {
        System.out.println("getCreditCardInterest by: " + creditCardBalance + " & " + creditCardType);
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
        return creditCardInterest;
    }
}
