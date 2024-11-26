package com.digitalfuturescorp.app;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;


import static org.junit.jupiter.api.Assertions.*;

public class AddressBookInterfaceTest {

    @Test
    @DisplayName("7a. DisplayMenu should print a list of menu options to the console")
    public void testDisplayMainMenu() {
        // Arrange
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        String expected = "1. Add Contact\n" +
                "2. Search Contacts\n" +
                "3. Edit Contact\n" +
                "4. Delete Contact\n" +
                "5. View All Contacts\n" +
                "6. Delete All Contacts\n" +
                "Q. Quit\n" +
                "Enter your choice: ";
        // Act
        AddressBookInterface.displayMenu(AddressBookInterface.mainMenu);
        // Assert
        assertEquals(expected, outContent.toString());
        // Restore system output to its original stream
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("7b. AcceptInput should return a string with the users choice")
    public void testInputIsReturnedWhenAcceptInputIsCalled() {
        // Arrange
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Act
        String result = AddressBookInterface.acceptInput();

        // Assert
        assertEquals(input, result);
    }

    @Test
    @DisplayName("7d. The editMenu should be displayed when the user selects to edit a contact")
    public void testDisplayEditMenu() {
        // Arrange
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        String expected = "1. Edit Name\n" +
                "2. Edit Phone Number\n" +
                "3. Edit Email Address\n" +
                "Q. Quit\n" +
                "Enter your choice: ";
        // Act
        AddressBookInterface.displayMenu(AddressBookInterface.EditMenu);
        // Assert
        assertEquals(expected, outContent.toString());
        // Restore system output to its original stream
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("ADDITIONAL: The ConfirmAction menu should be displayed when the user selects to delete all contacts")
    public void testDisplayConfirmActionMenu() {
        // Arrange
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        String expected = "Are you sure you want to delete all contacts?\n" +
                "1. Yes\n" +
                "2. No\n" +
                "Enter your choice: ";
        // Act
        AddressBookInterface.displayMenu(AddressBookInterface.ConfirmAction);
        // Assert
        assertEquals(expected, outContent.toString());
        // Restore system output to its original stream
        System.setOut(originalOut);
    }

}