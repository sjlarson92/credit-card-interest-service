package com.sasha.creditcardinterestservice.controllers;

import com.sasha.creditcardinterestservice.models.*;
import com.sasha.creditcardinterestservice.services.InterestService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/interest")
public class InterestController {

    InterestService interestService = new InterestService();

    @GetMapping("/customerAndCreditCard")
    public ArrayList<CustomerInterest> getCustomerAndCreditCardInterest(@RequestBody ArrayList<Customer> customers) {
        customers.forEach(customer ->
                System.out.println("Getting Customer and Credit Card interest for: " + customer.getFirstName())
        );
        return interestService.getCustomerAndCreditCardInterest(customers);
    }

    @GetMapping("/customerAndWallet")
    public ArrayList<CustomerInterest> getCustomerAndWalletInterest(@RequestBody ArrayList<Customer> customers) {
        customers.forEach(customer ->
                System.out.println("Getting Customer and Wallet interest for: " + customer.getFirstName())
        );
        return interestService.getCustomerAndWalletInterest(customers);
    }

}