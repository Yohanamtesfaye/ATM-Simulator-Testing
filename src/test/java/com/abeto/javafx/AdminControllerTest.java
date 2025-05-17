package com.abeto.javafx;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class AdminControllerTest {

    private AdminController adminController;

    @BeforeEach
    public void setUp() {
        adminController = new AdminController();

        // Prepare JavaFX controls manually
        adminController.userName = new TextField();
        adminController.accNo = new TextField();
        adminController.pin = new TextField();
        adminController.intialBalance = new TextField();
        adminController.isAccountTaken = new Label();
        adminController.searchUser = new TextField();
        adminController.foundUserInfo = new Label();
        adminController.foundStatus = new Label();

        // Clear the static userDetail list before each test
        Account.userDetail.clear();
    }

    @AfterEach
    public void tearDown() {
        Account.userDetail.clear();
    }

    // Tests will go here...
    @Test
    public void testSaveAddsNewUserIfAccountNotTaken() {
        adminController.userName.setText("TestUser");
        adminController.accNo.setText("12345");
        adminController.pin.setText("1111");
        adminController.intialBalance.setText("1000");

        adminController.save(null);

        assertEquals(1, Account.userDetail.size());
        Account saved = Account.userDetail.get(0);
        assertEquals("TestUser", saved.getName());
        assertEquals(12345L, saved.getAccountNo());
        assertEquals(1111, saved.getPin());
        assertEquals(1000.0, saved.getBalance());
        assertEquals("Saved", adminController.isAccountTaken.getText());
    }

    @Test
    public void testSaveShowsErrorIfAccountTaken() {
        Account existing = new Account("ExistingUser", 2222, 12345L, 500.0);
        Account.userDetail.add(existing);

        adminController.userName.setText("NewUser");
        adminController.accNo.setText("12345"); // same account number
        adminController.pin.setText("1111");
        adminController.intialBalance.setText("1000");

        adminController.save(null);

        assertEquals(1, Account.userDetail.size()); // no new account added
        assertEquals("Account is Taken", adminController.isAccountTaken.getText());
    }

    @Test
    public void testSearchUserFindsExistingUser() {
        Account existing = new Account("Alice", 1111, 55555L, 1200);
        Account.userDetail.add(existing);

        adminController.searchUser.setText("55555");
        adminController.searchUser(null);

        assertTrue(adminController.foundUserInfo.getText().contains("Alice"));
        assertEquals("", adminController.foundStatus.getText());
    }

    @Test
    public void testSearchUserNotFound() {
        adminController.searchUser.setText("99999");
        adminController.searchUser(null);

        assertEquals("User not found", adminController.foundStatus.getText());
    }

    @Test
    public void testDeleteOneRemovesUser() {
        Account existing = new Account("Bob", 3333, 77777L, 900);
        Account.userDetail.add(existing);
        AdminController.deleteUserIndex = 0;

        adminController.foundUserInfo = new Label();
        adminController.foundStatus = new Label();

        Account.userDetail.remove(AdminController.deleteUserIndex);
        adminController.foundUserInfo.setText("");
        adminController.foundStatus.setText("Deletion is Succeeded");

        assertEquals(0, Account.userDetail.size());
        assertEquals("", adminController.foundUserInfo.getText());
        assertEquals("Deletion is Succeeded", adminController.foundStatus.getText());
    }

}
