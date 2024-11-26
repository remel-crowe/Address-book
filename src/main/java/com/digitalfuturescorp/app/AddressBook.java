package com.digitalfuturescorp.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class AddressBook {

    private List<Contact> contacts = new ArrayList<>();

    public List<Contact> getContacts() {
        return contacts;
    }

    public String viewAllContacts() {
        StringBuilder result = new StringBuilder();
        contacts.sort(Comparator.comparing(Contact::getName));
        for (Contact contact : contacts) {
            if (!result.isEmpty()) {
                result.append("\n");
            }
            result.append(contact.toString());
        }
        return result.isEmpty() ? "No contacts found" : result.toString();
    }

    public Contact findContactByName(String name) throws Exception{
        for (Contact contact : contacts) {
            if (contact.getName().equals(name) || contact.getName().contains(name)) {
                return contact;
            }
        }
        throw new Exception("No contact found with the given name: " + name);
    }

    public void addContact(Contact contact) {
        if (!numberExists(contact.getPhoneNumber()) && !emailExists(contact.getEmailAddress())) {
            contacts.add(contact);
            return;
        }
        throw new IllegalArgumentException("Contact with this number/email already exists");
    }

    private boolean numberExists(String number) {
        for (Contact contact : contacts) {
            if (contact.getPhoneNumber().equals(number)) {
                return true;
            }
        }
        return false;
    }

    private boolean emailExists(String email) {
        for (Contact contact : contacts) {
            if (contact.getEmailAddress().equals(email)) {
                return true;
            }
        }
        return false;
    }

    private boolean isMatch(Contact contact, String detail) {
        // Helper functiopn to check if the contact matches the detail, reduces clutter in the searchContact method
        return contact.getName().contains(detail)
                || contact.getName().equals(detail)
                || contact.getPhoneNumber().equals(detail)
                || contact.getEmailAddress().equals(detail);
    }

    public String searchContact(String detail) {
        StringBuilder result = new StringBuilder();
        for (Contact contact : contacts) {
            if (isMatch(contact, detail)) {
                if (!result.isEmpty()) {
                    result.append("\n");
                }
                result.append(contact.toString());
            }
        }
        return result.isEmpty() ? "Contact not found" : result.toString();
    }

    public String deleteContact(String name) throws Exception {
        List<Contact> matchedContacts = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.getName().contains(name) || contact.getName().equals(name)) {
                matchedContacts.add(contact);
            }
        }

        if (matchedContacts.size() > 1) {
            throw new Exception("Multiple contacts found with the given name: " + name);
        } else if (matchedContacts.size() == 1) {
            contacts.remove(matchedContacts.get(0));
            return "Contact " + matchedContacts.get(0).getName() + " deleted";
        } else {
            throw new Exception("No contact found with the given name: " + name);
        }
    }

    public void updateEmail(String name, String email) throws Exception {
        Contact contact = findContactByName(name);
        contact.setEmailAddress(email);
    }

    public void updatePhoneNumber(String name, String phoneNumber) throws Exception {
        Contact contact = findContactByName(name);
        contact.setPhoneNumber(phoneNumber);
    }

    public void updateName(String name, String newName) throws Exception {
        Contact contact = findContactByName(name);
        contact.setName(newName);
    }

    public void deleteAllContacts() {
        contacts.clear();
    }

}
