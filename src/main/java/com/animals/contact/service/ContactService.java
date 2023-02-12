package com.animals.contact.service;

import com.animals.contact.entity.Contact;
import com.animals.contact.entity.User;
import com.animals.contact.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ContactService {
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    RelationshipService relationshipService;

    public Iterable<Contact> all() {
        return contactRepository.findAll();
    }

    public Optional<Contact> findById(Long id) {
        return contactRepository.findById(id);
    }

    @Transactional
    public void delete(Long contactId, User user)  {
        Optional<Contact> contact = contactRepository.findById(contactId);

        if (contact.isPresent()) {
            if (user.getId() == contact.get().getUser().getId()) {
                relationshipService.deleteAllById(contactId);
                contactRepository.delete(contact.get());
            }
        }
    }

    public Iterable<Contact> findByString(String search,Long userId) {
        return contactRepository.findAllByLastnameContainingAndUserIdOrFirstnameContainingAndUserIdOrTelContainingAndUserIdOrEmailContainingAndUserId(search,userId,search,userId,search,userId,search,userId);
    }

    public Contact add(Contact contact) {
        return contactRepository.save(contact);
    }
}
