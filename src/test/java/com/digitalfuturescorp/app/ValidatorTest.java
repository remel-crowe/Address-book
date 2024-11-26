package com.digitalfuturescorp.app;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ValidatorTest {

    @Nested
    @DisplayName("Contact Validation Tests")
    class ValidateContactTests {

        @Test
        @DisplayName("1b. Validator throws an exception when any parameter is null")
        public void exceptionIsThrownWhenNullIsPassed() {
            //Arrange
            String nullString = null;
            //Act and Assert
            assertThrows(IllegalArgumentException.class, () -> Validator.validateString(nullString));
        }

        @Test
        @DisplayName("1c. Validator throws an exception when any parameter is empty")
        public void exceptionIsThrownWhenEmptyIsPassed() {
            //Arrange
            String emptyString  = " ";
            //Act and Assert
            assertThrows(IllegalArgumentException.class, () -> Validator.validateString(emptyString));
        }

        @Test
        @DisplayName("1d. Validator throws an exception when a phone number is not in the correct format")
        public void exceptionIsThrownWhenPhoneNumberIsFormattedIncorrectly() {
            //Arrange
            String invalidPhoneNumber  = "+12.a";
            //Act and Assert
            assertThrows(IllegalArgumentException.class, () -> Validator.validatePhoneNumber(invalidPhoneNumber));
        }


        @Test
        @DisplayName("1e. Validator throws an execption when an email is formatted incorrectly")
        public void exceptionIsThrownWhenEmailIsFormattedIncorrectly() {
            //Arrange
            String invalidEmail = "test.com";
            //Act and Assert
            assertThrows(IllegalArgumentException.class, () -> Validator.validateEmail(invalidEmail));
        }

    }
}
