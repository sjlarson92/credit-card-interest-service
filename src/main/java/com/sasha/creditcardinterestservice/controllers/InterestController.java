package com.sasha.creditcardinterestservice.controllers;

import com.sasha.creditcardinterestservice.models.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/interest")
public class InterestController {

    @GetMapping("/byPerson")
    public void getTotalInterestByPerson(@RequestBody ArrayList<Customer> customers) {
        customers.forEach(customer -> System.out.println("customer is: " + customer.getFirstName()));
    }

}