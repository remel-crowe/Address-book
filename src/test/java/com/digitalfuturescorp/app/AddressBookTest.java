package com.digitalfuturescorp.app;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AddressBookTest {
    Contact testContact1;
    Contact testContact2;
    AddressBook testAddressBook;
    String testContactName1 = "John Doe";
    String testContactName2 = "Jane Doe";
    String testContactPhoneNumber1 = "+12345678901";
    String testContactPhoneNumber2 = "+12345678902";
    String testContactEmail1 = "john@mail.com";
    String testContactEmail2 = "jane@mail.com";

    @BeforeEach
    public void prep() {
        testAddressBook = new AddressBook();
        //Mock contact to create isolated tests
        testContact1 = Mockito.mock(Contact.class);
        when(testContact1.getName()).thenReturn(testContactName1);
        when(testContact1.getPhoneNumber()).thenReturn(testContactPhoneNumber1);
        when(testContact1.getEmailAddress()).thenReturn(testContactEmail1);
        //Mock contact 2 to create isolated tests
        testContact2 = Mockito.mock(Contact.class);
        when(testContact2.getName()).thenReturn(testContactName2);
        when(testContact2.getPhoneNumber()).thenReturn(testContactPhoneNumber2);
        when(testContact2.getEmailAddress()).thenReturn(testContactEmail2);
    }

    @AfterEach
    public void tearDown() {
        testAddressBook = null;
        testContact1 = null;
        testContact2 = null;
    }

    @Nested
    @DisplayName("Adding Contacts Tests")
    class AddContactTests {
        @Test
        @DisplayName("1f. AddressBook adds a contact when a valid contact is passed")
        public void contactIsAddedWhenValidContactIsPassed() {
            //Arrange
            //Act
            testAddressBook.addContact(testContact1);
            //Assert
            assertEquals(1, testAddressBook.getContacts().size());
        }

        @Test
        @DisplayName("1g. Multiple contacts are added when multiple valid contacts are passed")
        public void multipleContactsAreAddedWhenValidContactsArePassed() {
            //Arrange
            //Act
            testAddressBook.addContact(testContact1);
            testAddressBook.addContact(testContact2);
            //Assert
            assertEquals(2, testAddressBook.getContacts().size());
        }

        @Test
        @DisplayName("2a. A contact is not added when its phone number is not unique")
        public void contactIsNotAddedWhenPhoneNumberIsNotUnique() {
            //Arrange
            when(testContact2.getPhoneNumber()).thenReturn(testContactPhoneNumber1);
            //Act
            testAddressBook.addContact(testContact1);
            //Assert
            assertAll(
                    () -> assertEquals(1, testAddressBook.getContacts().size()),
                    () -> assertThrows(IllegalArgumentException.class, () -> testAddressBook.addContact(testContact2)
                    ));
        }

        @Test
        @DisplayName("2b. A contact is not added when its email address is not unique")
        public void contactIsNotAddedWhenEmailAddressIsNotUnique() {
            //Arrange
            when(testContact2.getEmailAddress()).thenReturn(testContactEmail1);
            //Act
            testAddressBook.addContact(testContact1);
            //Assert
            assertAll(
                    () -> assertEquals(1, testAddressBook.getContacts().size()),
                    () -> assertThrows(IllegalArgumentException.class, () -> testAddressBook.addContact(testContact2)
                    ));
        }

        @Test
        @DisplayName("2c. A contact is added if the name is the same as another contact but all other details are different")
        public void contactIsAddedIfOnlyNameIsDuplicated() {
            //Arrange
            String TestName = "John Doe";
            when(testContact2.getName()).thenReturn(TestName);
            //Act
            testAddressBook.addContact(testContact1);
            testAddressBook.addContact(testContact2);
            //Assert
            assertAll(
                    () -> assertEquals(2, testAddressBook.getContacts().size()),
                    () -> assertTrue(testAddressBook.getContacts().contains(testContact2))
            );
        }
    }

    @Nested
    @DisplayName("Viewing Contacts Tests")
    class ViewContactTests {

        @Test
        @DisplayName("3a. AddressBook returns a list of contacts when viewAllContacts is called")
        public void addressBookReturnsListOfContacts() throws Exception {
            //Arrange
            String expectedContact1 = "Name:John Doe, Email Address:john@mail.com, Phone Number:+12345678901}";
            String expectedContact2 = "Name:Jane Doe, Email Address:jane@mail.com, Phone Number:+12345678902}";
            when(testContact1.toString()).thenReturn(expectedContact1);
            when(testContact2.toString()).thenReturn(expectedContact2);
            //Act
            testAddressBook.addContact(testContact1);
            testAddressBook.addContact(testContact2);
            String expected = expectedContact2 + "\n" + expectedContact1;
            //Assert
            assertEquals(expected, testAddressBook.viewAllContacts());
        }

        @Test
        @DisplayName("3b. AddressBook returns 'No contacts found' when the contact list is empty")
        public void addressBookThrowsExceptionWhenContactListIsEmpty() {
            //Arrange
            //Act
            //Assert
            assertEquals("No contacts found", testAddressBook.viewAllContacts());
        }

        @Test
        @DisplayName("4a. Search contact should return a single contact when a valid name is passed and the user exists")
        public void userIsReturnedWhenValidNameIsProvided() {
            //Arrange
            String expected = "Name:John Doe, Email Address:john@mail.com, Phone Number:+12345678901";
            when(testContact1.toString()).thenReturn(expected);
            //Act
            testAddressBook.addContact(testContact1);
            //Assert
            assertEquals(expected, testAddressBook.searchContact("John Doe"));
        }


        @Test
        @DisplayName("4b. Search contact should return multiple contacts when a partial name matches more than one contact")
        public void multipleContactsReturnedWhenPartialNameIsGiven() {
            //Arrange
            String expectedContact1 = "Name:John Doe, Email Address:john@mail.com, Phone Number:+12345678901";
            String expectedContact2 = "Name:Jane Doe, Email Address:jane@mail.com, Phone Number:+12345678902";
            when(testContact1.toString()).thenReturn(expectedContact1);
            when(testContact2.toString()).thenReturn(expectedContact2);

            //Act
            String expected = expectedContact1 + "\n" + expectedContact2;
            testAddressBook.addContact(testContact1);
            testAddressBook.addContact(testContact2);
            //Assert
            assertEquals(expected, testAddressBook.searchContact("Doe"));
        }

        @Test
        @DisplayName("4c. Search contact should return 'Contact not found' when no contact matches the name")
        public void contactNotFoundWhenNameDoesNotExist() {
            //Arrange
            //Act
            //Assert
            assertEquals("Contact not found", testAddressBook.searchContact("John Doe"));
        }

        @Test
        @DisplayName("ADDITIONAL: Search contact should return a contact when a valid phone number is passed and the contact exists")
        public void contactIsReturnedWhenValidPhoneNumberIsProvided() {
            String expected = "Name:John Doe, Email Address:john@mail.com, Phone Number:+12345678901";
            when(testContact1.toString()).thenReturn(expected);
            //Act
            testAddressBook.addContact(testContact1);
            //Assert
            assertEquals(expected, testAddressBook.searchContact("+12345678901"));
        }

        @Test
        @DisplayName("ADDITIONAL: Search contact should return a contact when a valid email is passed and the contact exists")
        public void contactIsReturnedWhenValidEmailIsProvided() {
            String expected = "Name:John Doe, Email Address:john@mail.com, Phone Number:+12345678901";
            when(testContact1.toString()).thenReturn(expected);
            //Act
            testAddressBook.addContact(testContact1);
            //Assert
            assertEquals(expected, testAddressBook.searchContact("john@mail.com"));
        }

        @Nested
        @DisplayName("Delete Contact Tests")
        class DeleteContactTests {

            @Test
            @DisplayName("5a. A single contact is deleted when a valid name is passed and the contact exists")
            public void contactIsDeletedWhenValidNameIsProvided() {
                // Arrange
                testAddressBook.addContact(testContact1);
                String nameToDelete = "John Doe";

                // Act
                try {
                    testAddressBook.deleteContact(nameToDelete);
                } catch (Exception e) {
                    fail("Exception should not be thrown when a valid name is passed");
                }

                // Assert
                assertEquals(0, testAddressBook.getContacts().size());
            }

            @Test
            @DisplayName("5b. Exception is thrown when a name matching multiple contacts is passed")
            public void exceptionThrownWhenNameMatchesMultipleContacts() {
                // Arrange
                testAddressBook.addContact(testContact1);
                testAddressBook.addContact(testContact2);

                // Act
                Exception exception = assertThrows(Exception.class, () -> testAddressBook.deleteContact("Doe"));

                // Assert
                assertAll(
                        () -> assertEquals("Multiple contacts found with the given name: Doe", exception.getMessage()),
                        () -> assertEquals(2, testAddressBook.getContacts().size())
                );
            }


            @Test
            @DisplayName("5c. 'Contact not found' is returned when the contact does not exist")
            public void contactNotFoundWhenNameDoesNotExist() {
                //Arrange
                //Act
                //Assert
                assertEquals("Contact not found", testAddressBook.searchContact("Weird User"));
            }


            @Test
            @DisplayName("ADDITIONAL: All contacts are deleted when deleteAllContacts is called")
            public void allContactsAreDeletedWhenDeleteAllContactsIsCalled() {
                // Arrange
                testAddressBook.addContact(testContact1);
                testAddressBook.addContact(testContact2);

                // Act
                testAddressBook.deleteAllContacts();
                // Assert
                assertEquals(0, testAddressBook.getContacts().size());
            }


        }

        @Nested
        @DisplayName("Update Contact Tests")
        class UpdateContactTests {

            @Test
            @DisplayName("6a. findContactByName returns a contact when a valid name is passed, exception is thrown when contact does not exist")
            public void returnsContactWhenValidNameIsGiven() {
                // Arrange
                testAddressBook.addContact(testContact1);
                String nonExistentContactName = "Non Existent Contact";

                // Act
                Contact foundContact = null;
                try {
                    foundContact = testAddressBook.findContactByName(testContactName1);
                } catch (Exception e) {
                    fail("Exception should not be thrown when a valid name is passed");
                }

                // Assert
                assertEquals(testContact1, foundContact);
                Exception exception = assertThrows(Exception.class, () -> testAddressBook.findContactByName(nonExistentContactName));
                assertEquals("No contact found with the given name: " + nonExistentContactName, exception.getMessage());
            }

            @Test
            @DisplayName("6b. A contact's email is updated when a valid name and email are passed, exception is thrown when contact does not exist")
            public void updatesEmailWhenValidParametersArePassed() {
                // Arrange
                String newEmail = "john2@mail.com";
                when(testContact1.getEmailAddress()).thenReturn(newEmail);
                testAddressBook.addContact(testContact1);
                String nonExistentContactName = "Non Existent Contact";

                // Act
                try {
                    testAddressBook.updateEmail(testContactName1, newEmail);
                } catch (Exception e) {
                    fail("Exception should not be thrown when a valid name and email are passed");
                }

                // Assert
                verify(testContact1).setEmailAddress(newEmail);
                Exception exception = assertThrows(Exception.class, () -> testAddressBook.updateEmail(nonExistentContactName, newEmail));
                assertEquals("No contact found with the given name: " + nonExistentContactName, exception.getMessage());
            }

            @Test
            @DisplayName("6c. A contact's phone number is updated when a valid name and phone number are passed, exception is thrown when contact does not exist")
            public void updatesPhoneWhenValidParametersArePassed() {
                // Arrange
                String newPhoneNumber = "+12345678905";
                when(testContact1.getPhoneNumber()).thenReturn(newPhoneNumber);
                testAddressBook.addContact(testContact1);
                String nonExistentContactName = "Non Existent Contact";

                // Act
                try {
                    testAddressBook.updatePhoneNumber(testContactName1, newPhoneNumber);
                } catch (Exception e) {
                    fail("Exception should not be thrown when a valid name and phone number are passed");
                }

                // Assert
                verify(testContact1).setPhoneNumber(newPhoneNumber);
                Exception exception = assertThrows(Exception.class, () -> testAddressBook.updatePhoneNumber(nonExistentContactName, newPhoneNumber));
                assertEquals("No contact found with the given name: " + nonExistentContactName, exception.getMessage());
            }

            @Test
            @DisplayName("6d. A contact's name is updated when a valid name and new name are passed, exception is thrown when contact does not exist")
            public void updatesNameWhenValidParametersArePassed() {
                // Arrange
                String newName = "John Doe Jr";
                when(testContact1.getName()).thenReturn(newName);
                testAddressBook.addContact(testContact1);
                String nonExistentContactName = "Non Existent Contact";

                // Act
                try {
                    testAddressBook.updateName(testContactName1, newName);
                } catch (Exception e) {
                    fail("Exception should not be thrown when a valid name and new name are passed");
                }

                // Assert
                verify(testContact1).setName(newName);
                Exception exception = assertThrows(Exception.class, () -> testAddressBook.updateName(nonExistentContactName, newName));
                assertEquals("No contact found with the given name: " + nonExistentContactName, exception.getMessage());
            }

        }
    }
}


