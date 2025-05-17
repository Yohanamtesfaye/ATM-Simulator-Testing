package com.abeto.javafx;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    private UserController controller;

    // Initialize JavaFX Platform once before all tests (required for JavaFX
    // controls)
    @BeforeAll
    static void initToolkit() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(latch::countDown);
        latch.await();
    }

    @BeforeEach
    void setUp() {
        controller = new UserController();

        // Prepare UserAccount and userDetail list
        UserController.UserAccount = new Account("Test User", 1234, 123L, 1000.0);

        Account.userDetail = new ArrayList<>();
        Account.userDetail.add(UserController.UserAccount);

        // Add another account for testing transfer functionality
        Account anotherAccount = new Account("Receiver", 4321, 456L, 500.0);
        Account.userDetail.add(anotherAccount);

        // Initialize UI components used in controller
        controller.depositField = new TextField();
        controller.withdrawField = new TextField();
        controller.transferField = new TextField();
        controller.receiverAccountField = new TextField();
        controller.fieldStatus = new Label();
        controller.receiverStatus = new Label();
    }

    @Test
    void testDepositPositiveAmount() {
        controller.depositField.setText("200");
        controller.deposit(null);
        assertEquals(1200.0, UserController.UserAccount.getBalance(), 0.01);
        assertEquals("Done", controller.fieldStatus.getText());
        assertEquals("0x008000ff", controller.fieldStatus.getTextFill().toString()); // Color.GREEN in hex ARGB
    }

    @Test
    void testDepositNegativeAmount() {
        controller.depositField.setText("-50");
        controller.deposit(null);
        assertEquals(1000.0, UserController.UserAccount.getBalance(), 0.01);
        assertEquals("Please enter a proper amount", controller.fieldStatus.getText());
        assertEquals("0xff0000ff", controller.fieldStatus.getTextFill().toString()); // Color.RED in hex ARGB
    }

    @Test
    void testWithdrawSufficientBalance() {
        controller.withdrawField.setText("300");
        controller.withdraw(null);
        assertEquals(700.0, UserController.UserAccount.getBalance(), 0.01);
        assertEquals("Done", controller.fieldStatus.getText());
        assertEquals("0x008000ff", controller.fieldStatus.getTextFill().toString());
    }

    @Test
    void testWithdrawInsufficientBalance() {
        controller.withdrawField.setText("1500");
        controller.withdraw(null);
        assertEquals(1000.0, UserController.UserAccount.getBalance(), 0.01);
        assertEquals("Insufficient Balance", controller.fieldStatus.getText());
        assertEquals("0xff0000ff", controller.fieldStatus.getTextFill().toString());
    }

    @Test
    void testTransferValidAccountSufficientBalance() {
        controller.transferField.setText("400");
        controller.receiverAccountField.setText("456");

        controller.transfer(null);

        assertEquals(600.0, UserController.UserAccount.getBalance(), 0.01);
        assertEquals(900.0, Account.userDetail.get(1).getBalance(), 0.01);
        assertEquals("400.0$ is transferred to 456", controller.fieldStatus.getText());
        assertEquals("0x008000ff", controller.fieldStatus.getTextFill().toString());
        assertEquals("", controller.receiverStatus.getText());
    }

    @Test
    void testTransferInvalidAccount() {
        controller.transferField.setText("100");
        controller.receiverAccountField.setText("999");

        controller.transfer(null);

        assertEquals(1000.0, UserController.UserAccount.getBalance(), 0.01);
        assertEquals("Receiver account is't Found, Try again!!", controller.receiverStatus.getText());
        assertEquals("0xff0000ff", controller.receiverStatus.getTextFill().toString());
    }
}
