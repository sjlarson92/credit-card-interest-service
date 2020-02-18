package com.sasha.creditcardinterestservice.controllers;

import com.sasha.creditcardinterestservice.models.*;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/interest")
public class InterestController {

//    @GetMapping("/customerAndCreditCard")
//    public ArrayList<CustomerAndCreditCardInterest> getInterestByCustomerAndCreditCard(@RequestBody ArrayList<Customer> customers) {
//        ArrayList<CustomerAndCreditCardInterest> customerAndCreditCardInterestArray = new ArrayList<>();
//        customers.forEach(customer -> {
//
//            BigDecimal totalInterest = BigDecimal.valueOf(0);
//            Map<Integer, BigDecimal> interestByCreditCard = new HashMap<>();
//            ArrayList<Wallet> wallets = customer.getWallets();
//
//            for(Wallet wallet: wallets) {
//                ArrayList<CreditCard> creditCards = wallet.getCreditCards();
//                for(CreditCard creditCard : creditCards) {
//
//                    int creditCardBalance = creditCard.getBalance();
//                    CreditCard.Type creditCardType = creditCard.getType();
//
//                    BigDecimal creditCardInterest = BigDecimal.valueOf(0);
//                    switch (creditCardType) {
//                        case VISA:
//                            creditCardInterest = BigDecimal.valueOf(creditCardBalance * .1).setScale(2, RoundingMode.HALF_UP);
//                            break;
//                        case DISCOVER:
//                            creditCardInterest = BigDecimal.valueOf(creditCardBalance * .01).setScale(2, RoundingMode.HALF_UP);
//                            break;
//                        case MASTERCARD:
//                            creditCardInterest = BigDecimal.valueOf(creditCardBalance * .05).setScale(2, RoundingMode.HALF_UP);
//                            break;
//                    }
//                    interestByCreditCard.put(creditCard.getId(), creditCardInterest);
//                    totalInterest =  totalInterest.add(creditCardInterest);
//                }
//            }
//
//            CustomerAndCreditCardInterest customerAndCreditCardInterest = new CustomerAndCreditCardInterest(
//                    customer.getId(),
//                    customer.getFirstName(),
//                    customer.getLastName(),
//                    totalInterest,
//                    interestByCreditCard
//            );
//            customerAndCreditCardInterestArray.add(customerAndCreditCardInterest);
//        });
//
//
//        return customerAndCreditCardInterestArray;
//    }

    @GetMapping("/customerAndWallet")
    public ArrayList<CustomerAndWalletInterest> getInterestByCustomerAndWallet(@RequestBody ArrayList<Customer> customers) {
        ArrayList<CustomerAndWalletInterest> customerAndWalletInterestArray = new ArrayList<>();
        customers.forEach(customer -> {

            BigDecimal totalInterest = BigDecimal.valueOf(0);
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
                    totalInterest =  totalInterest.add(creditCardInterest);
                    walletInterest = walletInterest.add(creditCardInterest);
                }
                interestByWallet.put(wallet.getId(), walletInterest);
            }

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

}