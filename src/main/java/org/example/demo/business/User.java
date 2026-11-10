package org.example.demo.business;


import java.io.Serializable;
import java.util.Calendar;

public class User implements Serializable {
    private String firstName;
    private String lastName;
    private String email;
    private String dob;
    private String hearAboutUs;
    private String cdOffers;
    private String specialOffers;
    private String[] contactMethods;

    public User() {
        firstName = "";
        lastName = "";
        email = "";
        dob = "";
    }

    public User(String firstName, String lastName, String email, String dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;
    }

    public String getDob() {
        return dob;
    }
    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHearAboutUs() {return hearAboutUs;}
    public void setHearAboutUs(String hearAboutUs) { this.hearAboutUs = hearAboutUs; }

    public String[] getContactMethods() { return contactMethods; }
    public void setContactMethods(String[] contactMethods) { this.contactMethods = contactMethods; }

    public String getCdOffers() {
        return cdOffers;
    }

    public void setCdOffers(String cdOffers) {
        this.cdOffers = cdOffers;
    }

    public String getSpecialOffers() {
        return specialOffers;
    }

    public void setSpecialOffers(String specialOffers) {
        this.specialOffers = specialOffers;
    }
}


