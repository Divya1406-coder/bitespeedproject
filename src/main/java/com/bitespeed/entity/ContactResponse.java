package com.bitespeed.entity;

import java.util.List;

public class ContactResponse {
    private int primaryContactId;
    private List<String> emails;
    private List<String> phoneNumbers;
    private List<Integer> secondaryContactIds;

    public ContactResponse() {
    }

    public ContactResponse(int primaryContactId2, List<String> emails, List<String> phoneNumbers, List<Integer> secondaryContactIds) {
        this.primaryContactId = primaryContactId2;
        this.emails = emails;
        this.phoneNumbers = phoneNumbers;
        this.secondaryContactIds = secondaryContactIds;
    }

    // Getters and setters
}
