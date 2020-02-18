package com.sasha.creditcardinterestservice.controllers;

import com.sasha.creditcardinterestservice.models.CreditCard;
import com.sasha.creditcardinterestservice.models.Customer;
import com.sasha.creditcardinterestservice.models.CustomerInterest;
import com.sasha.creditcardinterestservice.models.Wallet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

@RestController
@RequestMapping("/interest")
public class InterestController {

    @GetMapping("/byPerson")
    public ArrayList<CustomerInterest> getTotalInterestByPerson(@RequestBody ArrayList<Customer> customers) {
        ArrayList<CustomerInterest> customerInterestArray = new ArrayList<>();
        customers.forEach(customer -> {

            BigDecimal totalInterest = BigDecimal.valueOf(0);
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

            CustomerInterest customerInterest = new CustomerInterest(
                    customer.getId(),
                    customer.getFirstName(),
                    customer.getLastName(),
                    totalInterest
            );
            customerInterestArray.add(customerInterest);
        });


        return customerInterestArray;
    }

}