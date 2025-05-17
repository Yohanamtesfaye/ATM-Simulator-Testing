package com.abeto.javafx;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account("Yohana", 1234, 1002003004L, 5000.00);
    }

    @Test
    void testConstructorInitialization() {
        assertEquals("Yohana", account.getName());
        assertEquals(1234, account.getPin());
        assertEquals(1002003004L, account.getAccountNo());
        assertEquals(5000.00, account.getBalance());
    }

    @Test
    void testSetAndGetName() {
        account.setName("Abeto");
        assertEquals("Abeto", account.getName());
    }

    @Test
    void testSetAndGetPin() {
        account.setPin(4321);
        assertEquals(4321, account.getPin());
    }

    @Test
    void testSetAndGetAccountNo() {
        account.setAccountNo(111222333L);
        assertEquals(111222333L, account.getAccountNo());
    }

    @Test
    void testSetAndGetBalance() {
        account.setBalance(1500.75);
        assertEquals(1500.75, account.getBalance());
    }

    @Test
    void testToStringFormat() {
        String output = account.toString();
        assertTrue(output.contains("Yohana"));
        assertTrue(output.contains("1002003004"));
        assertTrue(output.contains("1234"));
        assertTrue(output.contains("5000.0") || output.contains("5000"));
    }
}
