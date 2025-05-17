package com.abeto.javafx;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private Main mainApp;
    private final String testFilename = "user-data.txt";

    @BeforeEach
    void setUp() {
        mainApp = new Main();
    }

    @AfterEach
    void tearDown() throws Exception {
        // Clean up test file after each test run
        Files.deleteIfExists(Path.of(testFilename));
    }

    @Test
    void testCopyToFileWritesAccounts() throws Exception {
        ArrayList<Account> accounts = new ArrayList<>();
        accounts.add(new Account("Alice", 1234, 1111L, 1000));
        accounts.add(new Account("Bob", 4321, 2222L, 500));

        mainApp.copyToFile(accounts);

        // Check file content
        try (BufferedReader reader = new BufferedReader(new FileReader(testFilename))) {
            String firstLine = reader.readLine();
            String secondLine = reader.readLine();

            assertTrue(firstLine.contains("Alice"));
            assertTrue(firstLine.contains("1111"));
            assertTrue(secondLine.contains("Bob"));
            assertTrue(secondLine.contains("2222"));
        }
    }
}
