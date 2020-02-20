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
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(InterestController.class)
public class InterestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getCustomerAndCreditCardInterest_when1Customer1WalletWith100CCBalance_returnCorrectResult() throws Exception {

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

    @Test
    public void getCustomerAndWalletInterest_when1Customer2WalletsWith100CCBalance_returnCorrectResult() throws Exception {
        ArrayList<Customer> customers = new ArrayList<>();

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
        ArrayList<CreditCard> creditCards1 = new ArrayList<>(Arrays.asList(creditCard1, creditCard3));
        ArrayList<CreditCard> creditCards2 = new ArrayList<>(Collections.singletonList(creditCard2));

        Wallet wallet1 = new Wallet(
                1,
                creditCards1
        );
        Wallet wallet2 = new Wallet(
                2,
                creditCards2
        );
        ArrayList<Wallet> wallets = new ArrayList<>(Arrays.asList(wallet1, wallet2));
        Customer customer = new Customer(
                1,
                "Austin",
                "Powers",
                wallets
        );
        customers.add(customer);

        String customersContent = objectMapper.writeValueAsString(customers);
        System.out.println("customersContent: " + customersContent);

        MvcResult result = mockMvc.perform(
                get("/interest/customerAndWallet")
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

        assertThat(customerInterestArray.get(0).getInterestByType().get("wallet").get(1))
                .isEqualTo(BigDecimal.valueOf(11).setScale(2, RoundingMode.HALF_UP));

        assertThat(customerInterestArray.get(0).getInterestByType().get("wallet").get(2))
                .isEqualTo(BigDecimal.valueOf(5).setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    public void getCustomerAndWalletInterest_when2Customers1WalletEachWith100CCBalance_returnCorrectResult() throws Exception {
        CreditCard customer1creditCard1 = new CreditCard(
                1,
                CreditCard.Type.VISA,
                100
        );
        CreditCard customer1creditCard2 = new CreditCard(
                2,
                CreditCard.Type.MASTERCARD,
                100
        );
        CreditCard customer1creditCard3 = new CreditCard(
                3,
                CreditCard.Type.DISCOVER,
                100
        );
        ArrayList<CreditCard> customer1creditCards = new ArrayList<>(Arrays
                .asList(customer1creditCard1, customer1creditCard2, customer1creditCard3));

        Wallet customer1Wallet = new Wallet(
                1,
                customer1creditCards
        );

        ArrayList<Wallet> customer1Wallets = new ArrayList<>(Collections
                .singletonList(customer1Wallet));

        Customer customer1 = new Customer(
                1,
                "Natasha",
                "Romanoff",
                customer1Wallets
        );

        CreditCard customer2creditCard1 = new CreditCard(
                1,
                CreditCard.Type.VISA,
                100
        );

        CreditCard customer2creditCard2 = new CreditCard(
                2,
                CreditCard.Type.MASTERCARD,
                100
        );

        ArrayList<CreditCard> customer2creditCards = new ArrayList<>(Arrays
                .asList(customer2creditCard1, customer2creditCard2));

        Wallet customer2Wallet = new Wallet(
                1,
                customer2creditCards
        );

        ArrayList<Wallet> customer2Wallets = new ArrayList<>(Collections
                .singletonList(customer2Wallet));

        Customer customer2 = new Customer(
                2,
                "Evelyn",
                "Salt",
                customer2Wallets
        );
        ArrayList<Customer> customers = new ArrayList<>(Arrays.asList(customer1, customer2));

        String customersContent = objectMapper.writeValueAsString(customers);
        System.out.println("customersContent: " + customersContent);

        MvcResult result = mockMvc.perform(
                get("/interest/customerAndWallet")
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
        assertThat(customerInterestArray.get(0).getInterestByType().get("wallet").get(1))
                .isEqualTo(BigDecimal.valueOf(16).setScale(2, RoundingMode.HALF_UP));

        assertThat(customerInterestArray.get(1).getTotalInterest())
                .isEqualTo(BigDecimal.valueOf(15).setScale(2, RoundingMode.HALF_UP));
        assertThat(customerInterestArray.get(1).getInterestByType().get("wallet").get(1))
                .isEqualTo(BigDecimal.valueOf(15).setScale(2, RoundingMode.HALF_UP));
    }
}
