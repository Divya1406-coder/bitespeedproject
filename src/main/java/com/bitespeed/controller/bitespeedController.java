package com.bitespeed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bitespeed.entity.*;
import com.bitespeed.repo.ContactRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/identify")
public class bitespeedController {

    private final ContactRepository contactRepository;

    @Autowired
    public bitespeedController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @PostMapping
    public ResponseEntity<IdentificationResponse> identify(@RequestBody IdentifyRequest request) {
        String email = request.getEmail();
        String phoneNumber = request.getPhoneno();

        List<Contact> matchingContacts = new ArrayList<>();
        if (email != null) {
            matchingContacts.addAll(contactRepository.findByEmail(email));
        }
        if (phoneNumber != null) {
            matchingContacts.addAll(contactRepository.findByPhoneNumber(phoneNumber));
        }

        if (matchingContacts.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        IdentificationResponse identificationResponse = consolidateContacts(matchingContacts);
        return ResponseEntity.ok(identificationResponse);
    }

    private IdentificationResponse consolidateContacts(List<Contact> matchingContacts) {
        List<String> emails = new ArrayList<>();
        List<String> phoneNumbers = new ArrayList<>();
        List<Integer> secondaryContactIds = new ArrayList<>();
        int primaryContactId = 0;

        for (Contact contact : matchingContacts) {
            if (primaryContactId == 0) {
                primaryContactId = contact.getId();
            } else {
                secondaryContactIds.add(contact.getId());
            }

            if (contact.getEmail() != null) {
                emails.add(contact.getEmail());
            }

            if (contact.getPhoneNumber() != null) {
                phoneNumbers.add(contact.getPhoneNumber());
            }
        } 

        ContactResponse contactResponse = new ContactResponse(primaryContactId, emails, phoneNumbers, secondaryContactIds);
        IdentificationResponse identificationResponse = new IdentificationResponse(contactResponse);
        return identificationResponse;
    }
}
