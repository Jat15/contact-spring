package com.animals.contact.service;

import com.animals.contact.entity.Contact;
import com.animals.contact.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContactService {
    @Autowired
    ContactRepository contactRepository;

    public Iterable<Contact> all() {
        return contactRepository.findAll();
    }

    public Optional<Contact> findById(Long id) {
        return contactRepository.findById(id);
    }
}
