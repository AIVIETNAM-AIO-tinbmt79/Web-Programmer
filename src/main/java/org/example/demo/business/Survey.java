package org.example.demo.business;

import java.io.Serializable;

public class Survey implements Serializable {
    private String email;
    private String hearAboutUs;
    private String cdOffers;
    private String specialOffers;
    private String[] contactMethods;

    public Survey() {
        email = "";
        hearAboutUs = "";
        cdOffers = "";
        specialOffers = "";
        contactMethods = new String[0];
    }

    public Survey(String email, String hearAboutUs, String cdOffers, String specialOffers, String[] contactMethods){
        this.email = email;
        this.hearAboutUs = hearAboutUs;
        this.cdOffers = cdOffers;
        this.specialOffers = specialOffers;
        this.contactMethods = contactMethods;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHearAboutUs() {
        return hearAboutUs;
    }

    public void setHearAboutUs(String hearAboutUs) {
        this.hearAboutUs = hearAboutUs;
    }

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

    public String[] getContactMethods() {
        return contactMethods;
    }

    public void setContactMethods(String[] contactMethods) {
        this.contactMethods = contactMethods;
    }
}