package com.digitalfuturescorp.app;

// The Contact class is used to create contact objects. Each contact has a name, email address, and phone number.
public class Contact {

    private String name;
    private String emailAddress;
    private String phoneNumber;

    public Contact(String name, String email, String phone) {
        this.name = name;
        this.emailAddress = email;
        this.phoneNumber = phone;
    }

    public String getName() {
        return name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Name:'" + name + ", Email Address:" + emailAddress + ", Phone Number:'" + phoneNumber;
    }

}
