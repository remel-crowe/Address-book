package com.digitalfuturescorp.app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

public class ContactTest {

    private String testName = "John Doe";
    private String testEmail = "test@test.com";
    private String testPhone = "+12345678901";

    @DisplayName("Test Contact constructor")
    @Nested
    class TestContactConstructor {
        @Test
        @DisplayName("1a. Test constructor correctly assigns name, email, and phone number")
        public void testConstructorCorrectlyAssignsArguments() {
            Contact contact = new Contact(testName, testEmail, testPhone);
            assertAll(
                    () -> assertEquals(testName, contact.getName()),
                    () -> assertEquals(testEmail, contact.getEmailAddress()),
                    () -> assertEquals(testPhone, contact.getPhoneNumber())
            );
        }
    }


}
