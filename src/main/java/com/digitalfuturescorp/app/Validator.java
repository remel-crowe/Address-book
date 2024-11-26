package com.digitalfuturescorp.app;

// The Validator class is used to validate user input. It contains methods to validate strings, phone numbers, and email addresses.

public class Validator {
    protected static boolean validateString(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Input must not be null or empty");
        }
        return true;
    }
    protected static boolean validatePhoneNumber (String phoneNumber ) {
        validateString(phoneNumber);
        if (!phoneNumber.matches("^\\+\\d{1,2}\\d{10}$")) {
            throw new IllegalArgumentException("Phone number must be in the format +12345678901");
        }
        return true;
    }

    protected static boolean validateEmail(String email) {
        validateString(email);
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            throw new IllegalArgumentException("Email must be in the format name@email.com");
        }
        return true;
    }
}
