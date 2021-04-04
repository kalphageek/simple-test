package me.kalpha.enumsample.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AffiliateTest {
    @Test
    public void enumtest() {
        Affiliate.Code skcc = Affiliate.Code.SKCC;
        Affiliate.Code skt = Affiliate.Code.SKT;
        Affiliate.Code st11 = Affiliate.Code.ST11;

        Affiliate.Code skccValueOf = Affiliate.Code.valueOf("SKCC");

        assertTrue(skcc.getViewName().equals("SK C&C"));
        assertTrue(skt.getCompanyCode().equals("w01"));
        assertTrue(st11.getBizType().equals("s020"));
        assertTrue(skccValueOf.getViewName().equals("SK C&C"));
    }

}