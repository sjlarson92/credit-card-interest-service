package com.sasha.creditcardinterestservice.controllers;


import com.sasha.creditcardinterestservice.models.CreditCard;
import com.sasha.creditcardinterestservice.models.Customer;
import com.sasha.creditcardinterestservice.models.CustomerInterest;
import com.sasha.creditcardinterestservice.models.Wallet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InterestController.class)
public class InterestControllerTest {
    InterestController interestController = new InterestController();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getCustomerAndCreditCardInterest_when100CCBalanceAndVISACCType_returnTotalInterest10() throws Exception {
        System.out.println("Hello World");
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Wallet> wallets = new ArrayList<>();
        ArrayList<CreditCard> creditCards = new ArrayList<>();

        CreditCard creditCard = new CreditCard(
                1,
                CreditCard.Type.VISA,
                100
        );
        creditCards.add(creditCard);
        Wallet wallet = new Wallet(
                1,
                creditCards
        );
        wallets.add(wallet);
        Customer customer = new Customer(
                1,
                "James",
                "Bond",
                wallets
        );
        customers.add(customer);

        String content = objectMapper.writeValueAsString(customers);

        MvcResult result = mockMvc.perform(
                get("/interest/customerAndCreditCard")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(content)
                )
                .andExpect(status().isOk())
                .andReturn();

        System.out.println("result: " + result.getResponse().getContentAsString());

//        System.out.println("creditCardInterest: " + creditCardInterest.get(0).getTotalInterest());
//        assertThat(creditCardInterest.get(0).getTotalInterest()).isEqualTo(BigDecimal.valueOf(10).setScale(2, RoundingMode.HALF_UP));
    }

}
