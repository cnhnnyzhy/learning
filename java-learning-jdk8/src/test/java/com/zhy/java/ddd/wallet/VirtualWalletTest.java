package com.zhy.java.ddd.wallet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class VirtualWalletTest {

    private VirtualWallet virtualWalletUnderTest;

    @Before
    public void setUp() {
        virtualWalletUnderTest = new VirtualWallet(0L);
    }

    @Test
    public void testGetBalance() {
        // Setup
        final BigDecimal expectedResult = new BigDecimal("0.00");

        // Run the test
        final BigDecimal result = virtualWalletUnderTest.getBalance();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAvaliableBalance() {
        // Setup
        final BigDecimal expectedResult = new BigDecimal("0.00");

        // Run the test
        final BigDecimal result = virtualWalletUnderTest.getAvaliableBalance();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testDebit() {
        // Setup

        // Run the test
        virtualWalletUnderTest.debit(new BigDecimal("1.00"));

        // Verify the results
    }

    @Test
    public void testCredit() {
        // Setup

        // Run the test
        virtualWalletUnderTest.credit(new BigDecimal("10.00"));

        Assert.assertEquals(10, virtualWalletUnderTest.getBalance().intValue());
        // Verify the results
    }
}
