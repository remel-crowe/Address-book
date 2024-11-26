package com.digitalfuturescorp.app;


public class App {
    public static void main(String[] args) throws Exception {

        AddressBook addressBook = new AddressBook();
        String choice;

        // Task 5: Adding Default Contacts - so all options can be used in the menu.
        Contact defaultContact1 = new Contact("John Doe", "john@mail.com", "+12345678901");
        Contact defaultContact2 = new Contact("Jane Doe", "jane@mail.com", "+12345678902");
        addressBook.addContact(defaultContact1);
        addressBook.addContact(defaultContact2);

        // This is the main program loop that runs until a user decides to quit, using the Q option.
        do {
            AddressBookInterface.displayMenu(AddressBookInterface.mainMenu);
            choice = AddressBookInterface.acceptInput();
            switch (choice) {
                case "1":
                    addContactAction(addressBook);
                    break;
                case "2":
                    searchContactAction(addressBook);
                    break;
                case "3":
                    editContactAction(addressBook);
                    break;
                case "4":
                    deleteContactAction(addressBook);
                    break;
                case "5":
                    viewAllContactsAction(addressBook);
                    break;
                case "6":
                    deleteAllContactsAction(addressBook);
                    break;
                case "Q":
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Invalid input. Please enter an option between 1 and 6. Press Q to quit.");
            }
        } while (!choice.equals("Q"));
        }

    //Actions that represent the menu options, these combine predefined methods created in other classes.
    // The implementation of these methods helps to reduce clutter in the main method and improve readability.
    public static void addContactAction(AddressBook addressBook) {
        String name;
        String phoneNumber;
        String emailAddress;
        try {
            System.out.print("Enter name: ");
            name = AddressBookInterface.acceptInput();
            Validator.validateString(name);

            System.out.print("Enter email address: ");
            emailAddress = AddressBookInterface.acceptInput();
            Validator.validateEmail(emailAddress);

            System.out.print("Enter phone number: e.g. +12345678901:");
            phoneNumber = AddressBookInterface.acceptInput();
            Validator.validatePhoneNumber(phoneNumber);

            Contact contact = new Contact(name, emailAddress, phoneNumber);
            addressBook.addContact(contact);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void searchContactAction(AddressBook addressBook) {
        String name;
        try {
            System.out.print("Enter name: ");
            name = AddressBookInterface.acceptInput();
            Validator.validateString(name);
            System.out.println(addressBook.searchContact(name));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void editContactAction(AddressBook addressBook) {
        try {
            System.out.print("Enter name: ");
            String name = AddressBookInterface.acceptInput();
            Validator.validateString(name);

            // Check if the contact exists before trying to update
            try {
                addressBook.findContactByName(name);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return;
            }

            AddressBookInterface.displayMenu(AddressBookInterface.EditMenu);
            String choice = AddressBookInterface.acceptInput();
            switch (choice) {
                case "1":
                    System.out.print("Enter new name: ");
                    String newName = AddressBookInterface.acceptInput();
                    Validator.validateString(newName);
                    addressBook.updateName(name, newName);
                    break;
                case "2":
                    System.out.print("Enter new phone number: ");
                    String newPhoneNumber = AddressBookInterface.acceptInput();
                    Validator.validatePhoneNumber(newPhoneNumber);
                    addressBook.updatePhoneNumber(name, newPhoneNumber);
                    break;
                case "3":
                    System.out.print("Enter new email: ");
                    String newEmail = AddressBookInterface.acceptInput();
                    Validator.validateEmail(newEmail);
                    addressBook.updateEmail(name, newEmail);
                    break;
                case "Q":
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Invalid input. Please enter an option between 1 and 3. Press Q to quit.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteContactAction(AddressBook addressBook) {
        String name;
        try {
            System.out.print("Enter name: ");
            name = AddressBookInterface.acceptInput();
            Validator.validateString(name);
            addressBook.deleteContact(name);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void viewAllContactsAction(AddressBook addressBook) {
        System.out.println(addressBook.viewAllContacts());
    }

    public static void deleteAllContactsAction(AddressBook addressBook) {
        try {
            AddressBookInterface.displayMenu(AddressBookInterface.ConfirmAction);
            String choice = AddressBookInterface.acceptInput();
            if (choice.equals("1")) {
                addressBook.deleteAllContacts();
            } else if (choice.equals("2")) {
                System.out.println("Action cancelled.");
            } else {
                System.out.println("Invalid input. Please enter 1 or 2.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }








}
