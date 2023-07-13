package com.bitespeed.entity;

public class IdentificationResponse {
    private ContactResponse contact;

    public IdentificationResponse() {
    }

    public IdentificationResponse(ContactResponse contact) {
        this.contact = contact;
    }

    public ContactResponse getContact() {
        return contact;
    }

    public void setContact(ContactResponse contact) {
        this.contact = contact;
    }
}
