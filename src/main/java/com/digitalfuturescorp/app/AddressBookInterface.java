package com.digitalfuturescorp.app;
import java.util.Scanner;

// The AddressBookInterface class is used to display the menu options and accept user input. It contains static methods to display the menu and accept user input.

public abstract class AddressBookInterface {

    AddressBookInterface() {
    }
    // The menu options are stored as static strings and printed to the console using the displayMenu method.
    static String mainMenu = "" +
            "1. Add Contact\n" +
            "2. Search Contacts\n" +
            "3. Edit Contact\n" +
            "4. Delete Contact\n" +
            "5. View All Contacts\n" +
            "6. Delete All Contacts\n" +
            "Q. Quit\n" +
            "Enter your choice: ";


    static String EditMenu = "" +
            "1. Edit Name\n" +
            "2. Edit Phone Number\n" +
            "3. Edit Email Address\n" +
            "Q. Quit\n" +
            "Enter your choice: ";


    static String ConfirmAction = "" +
            "Are you sure you want to delete all contacts?\n" +
            "1. Yes\n" +
            "2. No\n" +
            "Enter your choice: ";


    public static void displayMenu(String menu) {
        System.out.print(menu);
    }

    public static String acceptInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }













}
