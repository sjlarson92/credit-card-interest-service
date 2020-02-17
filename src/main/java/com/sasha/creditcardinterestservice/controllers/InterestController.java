package com.sasha.creditcardinterestservice.controllers;

import com.sasha.creditcardinterestservice.models.Customer;
import com.sasha.creditcardinterestservice.models.CustomerInterest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/interest")
public class InterestController {

    @GetMapping("/byPerson")
    public ArrayList<CustomerInterest> getTotalInterestByPerson(@RequestBody ArrayList<Customer> customers) {
        customers.forEach(customer -> System.out.println("customer is: " + customer.getFirstName()));
        ArrayList<CustomerInterest> customerInterestArray = new ArrayList<>();

        CustomerInterest customerInterest = new CustomerInterest(1, "Lucas", "Costa", 100);
        customerInterestArray.add(customerInterest);
        return customerInterestArray;
    }

}