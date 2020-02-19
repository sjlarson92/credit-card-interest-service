package com.sasha.creditcardinterestservice.services;

import com.sasha.creditcardinterestservice.models.CreditCard;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.assertj.core.api.Assertions.assertThat;

public class InterestServiceTest {
    InterestService interestService = new InterestService();

    @Test
    public void getCreditCardInterest_when100CCBalanceAndVISACCType_return10() {
        BigDecimal creditCardInterest = interestService.getCreditCardInterest(100, CreditCard.Type.VISA);
        System.out.println("creditCardInterest: " + creditCardInterest);
        assertThat(creditCardInterest).isEqualTo(BigDecimal.valueOf(10).setScale(2, RoundingMode.HALF_UP));
    }
}