package me.kalpha.enumsample.entity;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PaymentGroupTest {
    @Test
    public void paymentGroupTest() {
        //given
        PaymentOption toss = PaymentOption.TOSS;
        PaymentOption simplePay = PaymentOption.SIMPLE_PAY;
        PaymentOption point = PaymentOption.POINT;

        //when
        PaymentGroup tossGroup = PaymentGroup.findGroup(toss);
        PaymentGroup simplePayGroup = PaymentGroup.findGroup(simplePay);
        PaymentGroup pointGroup = PaymentGroup.findGroup(point);

        //then
        assertTrue(tossGroup.getViewName().equals("현금"));
        assertTrue(simplePayGroup.getViewName().equals("결제대행사"));
        assertTrue(pointGroup.getViewName().equals("기타"));

        Arrays.stream(PaymentGroup.values()).forEach(System.out::println);
        Arrays.stream(PaymentOption.values()).forEach(System.out::println);
    }

}