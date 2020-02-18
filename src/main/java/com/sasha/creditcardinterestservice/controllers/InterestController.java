package com.sasha.creditcardinterestservice.controllers;

import com.sasha.creditcardinterestservice.models.*;
import com.sasha.creditcardinterestservice.services.InterestService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/interest")
public class InterestController {

    InterestService interestService = new InterestService();

    @GetMapping("/customerAndCreditCard")
    public ArrayList<CustomerAndCreditCardInterest> getInterestByCustomerAndCreditCard(@RequestBody ArrayList<Customer> customers) {
        ArrayList<CustomerAndCreditCardInterest> customerAndCreditCardInterestArray = new ArrayList<>();
        customers.forEach(customer -> {

            BigDecimal totalInterest = interestService.getCustomerTotalInterest(customer);
            Map<Integer, BigDecimal> interestByCreditCard = interestService.getInterestByCreditCardId(customer);

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

    @GetMapping("/customerAndWallet")
    public ArrayList<CustomerAndWalletInterest> getInterestByCustomerAndWallet(@RequestBody ArrayList<Customer> customers) {
        ArrayList<CustomerAndWalletInterest> customerAndWalletInterestArray = new ArrayList<>();
        customers.forEach(customer -> {

            BigDecimal totalInterest = interestService.getCustomerTotalInterest(customer);
            Map<Integer, BigDecimal> interestByWallet = interestService.getInterestByWalletId(customer);

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