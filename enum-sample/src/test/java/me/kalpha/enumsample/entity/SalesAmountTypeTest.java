package me.kalpha.enumsample.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SalesAmountTypeTest {
    @Test
    public void saleAmountTest() {
        long txAmount = 10000L;

        long originAmount = SalesAmountType.ORIGIN_AMT.calculate(txAmount);
        assertTrue(originAmount == txAmount);

        long supplyAmount = SalesAmountType.SUPPLY_AMT.calculate(txAmount);
        assertTrue(supplyAmount == 9091L);

        long vatAmount = SalesAmountType.VAT_AMT.calculate(txAmount);
        assertTrue(vatAmount == 909L);

        long notUsed = SalesAmountType.NOT_USED.calculate(txAmount);
        assertTrue(notUsed == 0L);


    }

}