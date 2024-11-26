# Domain Models, Class Diagrams and Test Plan

## Class Diagram

```mermaid
---
title: Challenge 3 - Address Book Challenge
---
classDiagram
    
    class Contact {
        -name String
        -emailAddress String
        -phoneNumber String
        +Contact(name: String, email: String, phone: String)
        +getName() String
        +getEmailAddress() String
        +getPhoneNumber() String
        +setName(name: String) void
        +setEmail(emailAddress: String) void
        +setPhone(phoneNumber: String) void
        +toString() String
    }
    class AddressBook {
        -contacts ArrayList<>
        +getContacts() ArrayList<Contact>
        +viewAllContacts()  String
        +findContactByName(name: String) Contact
        +addContact(contact: Contact)  void
        -numberExists(number: String)  boolean
        -emailExists(email: String)  boolean
        +searchContact(name: String) String
        +deleteContact(name: String) String
        +updateEmail(name: String, email: String) void
        +updatePhoneNumber(name: String, phoneNumber: String) void
        +updateName(name: String, newName: String) void
    }
    
    class App {
        +main(args: String[]) void
        #addContactAction(AddressBook addressBook) void
        #viewAllContactsAction(AddressBook addressBook) void
        #searchContactAction(AddressBook addressBook) void
        #deleteContactAction(AddressBook addressBook) void
        #viewAllContactsAction(AddressBook addressBook) void
        #deleteAllContactsAction(AddressBook addressbook) void
    }

    class AddressBookInterface{
        <<abstract>>
        #mainMenu: String
        #editMenu: String
        #confirmAction: String
        +AddressBookInterface() void
        #displayMenu(String menu) void
        #acceptInput() String
    }   
    
    class Validator {
        <<abstract>>
        #ValidateString(name: String) boolean
        #ValidatePhoneNumber(phone: String) boolean
        #ValidateEmail(email: String) boolean
    }
    
    App --> Contact
    App --> AddressBook
    App --> AddressBookInterface
    App --> Validator
```

## User Stories and Test Plans

**1. As a user, I want to be able to add a contact to the address book, with at least a name, email and phone number, so that I can keep track of my contacts.**<br>
 - [ ] a. Contact constructor should correctly set the name, email and phone number of the contact.
 - [ ] b. Validator should throw an exception when a null element is passed as a parameter.
 - [ ] c. Validator should throw an exception when an empty element is passed as a parameter.
 - [ ] d. Validator should throw an exception when a phone number is formatted incorrectly.
 - [ ] e. Validator should throw an exception when an email is formatted incorrectly.
 - [ ] f. Create a new contact with a valid name, email and phone number, and add it to the address book. Verify that the address book length increases by 1.
 - [ ] g. Add two contacts to the address book and expect the length of contacts to be 2.
 - [ ] h. AddContact should print a success message when a contact is added successfully.<br><br>
**2. As a user, I want to only store contacts with unique phone numbers and emails, so that I don't duplicate contacts.**<br>
 - [ ] a. Add a contact with a phone number that already exists in the address book. Expect an exception to be thrown.
 - [ ] b. Add a contact with an email that already exists in the address book. Expect an exception to be thrown.
 - [ ] c. Add a contact with a duplicate name and expect the contact to be added successfully.<br><br>
**3. As a user, I want to be able to view all contacts in my address book, so that I can get a general contact overview.**<br>
 - [ ] a. ViewAllContacts should return a list of all contacts in the address book.
 - [ ] b. ViewAllContacts should throw an exception when the address book is empty.<br><br>
**4. As a user, I want to be able to search for a user by name, so that I can pull up details about a specific contact.**<br>
 - [ ] a. SearchContact should return a single contact when a valid name is passed as a parameter and the contact exists in the address book.
 - [ ] b. SearchContact should return multiple contacts when a partial name is passed and multiple contacts exist in the address book.
 - [ ] c. SearchContact should return an error message when the contact does not exist in the address book.<br><br>
**5. As a user, I want to be able to remove a contact from my address book, so that I no longer have that persons details.**<br>
 - [ ] a. RemoveContact should remove a single contact from the address book when a valid name is passed as a parameter.
 - [ ] b. RemoveContact should not remove any contacts when a name matching more than one contact is passed as a parameter.
 - [ ] c. RemoveContact should return an error message when the contact does not exist in the address book.<br><br>
**6. As a user, I want to be able to edit a contacts details, so that I can keep them up to date.**<br>
 - [ ] a. findContactByName should return a contact when a valid name is passed and throw an exception when the contact doesnt exist.
 - [ ] a. UpdateEmail should update the email of a contact when a valid name and email is passed as a parameter or throw an exception if the contact doesn't exist.
 - [ ] b. UpdatePhone should update the phone number of a contact when a valid name and phone are passed as a parameter or throw an exception if the contact doesn't exist.
 - [ ] c. UpdateName should update the name of a contact when a valid name and new name are passed as a parameter or throw an exception if the contact doesn't exist.<br><br>
**7. As a user, I want to be able to interact with this application through a command line interface, so that I can easily add, view, search, remove and edit contacts.**<br>
 - [ ] a. DisplayMenu should print a list of main options to the console.
 - [ ] b. AcceptInput should return the user input as a string.
 - [ ] c. The application should alert the user when an incorrect option has been pressed.
 - [ ] d. The editMenu should be shown when the user selects the edit contact option.

### Additional Criteria:

**8. As a user, I want to be able to search a user by phone number, so that I can find other details about specific users**<br>
- [ ] a. SearchContact should accept a phone number as an argument and return a string if it matches a contact.

**9. As a user, I want to be able to search a user by email address, so that I can find other details about specific users**<br>
- [ ] a. SearchContact should accept an email address as an argument and return a string if it matches a contact.

**10. As a user, I want to be able to delete all users at once, so that I can clear my addressbook**<br>
- [ ] a. The user should be prompted to confirm they want to delete all contacts.
- [ ] b. deleteAllUsers should remove every contact from the addressbook when the user confirms

