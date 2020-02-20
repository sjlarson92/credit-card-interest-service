package com.sasha.creditcardinterestservice.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sasha.creditcardinterestservice.models.CreditCard;
import com.sasha.creditcardinterestservice.models.Customer;
import com.sasha.creditcardinterestservice.models.CustomerInterest;
import com.sasha.creditcardinterestservice.models.Wallet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(InterestController.class)
public class InterestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getCustomerAndCreditCardInterest_when100CCBalanceAndVISACCType_returnCorrectResult() throws Exception {

        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Wallet> wallets = new ArrayList<>();

        CreditCard creditCard1 = new CreditCard(
                1,
                CreditCard.Type.VISA,
                100
        );
        CreditCard creditCard2 = new CreditCard(
                2,
                CreditCard.Type.MASTERCARD,
                100
        );
        CreditCard creditCard3 = new CreditCard(
                3,
                CreditCard.Type.DISCOVER,
                100
        );
        ArrayList<CreditCard> creditCards = new ArrayList<>(Arrays.asList(creditCard1, creditCard2, creditCard3));
        System.out.println("creditCards: " + creditCards.get(1));
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

        String customersContent = objectMapper.writeValueAsString(customers);
        System.out.println("customersContent: " + customersContent);

        MvcResult result = mockMvc.perform(
                get("/interest/customerAndCreditCard")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(customersContent)
                )
                .andReturn();

        String customerInterests = result.getResponse().getContentAsString();
        System.out.println("customerInterests: " + customerInterests);
        ArrayList<CustomerInterest> customerInterestArray = objectMapper
                .readValue(customerInterests, new TypeReference<>() {});

        assertThat(customerInterestArray.get(0).getTotalInterest())
                .isEqualTo(BigDecimal.valueOf(16).setScale(2, RoundingMode.HALF_UP));

        assertThat((customerInterestArray.get(0).getInterestByType().get("creditCard").get(1)))
                .isEqualTo(BigDecimal.valueOf(10).setScale(2, RoundingMode.HALF_UP));

        assertThat((customerInterestArray.get(0).getInterestByType().get("creditCard").get(2)))
                .isEqualTo(BigDecimal.valueOf(5).setScale(2, RoundingMode.HALF_UP));

        assertThat((customerInterestArray.get(0).getInterestByType().get("creditCard").get(3)))
                .isEqualTo(BigDecimal.valueOf(1).setScale(2, RoundingMode.HALF_UP));
    }

}
