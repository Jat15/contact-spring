package com.animals.contact.service;

import com.animals.contact.entity.Contact;
import com.animals.contact.entity.Relationship;
import com.animals.contact.entity.Tag;
import com.animals.contact.entity.User;
import com.animals.contact.repository.RelationshipRepository;
import com.animals.contact.repository.UserRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FakerService {
    @Autowired
    ContactService contactService;
    @Autowired
    TagService tagService;
    @Autowired
    RelationshipRepository relationshipRepository;

    private List<Contact> contacts = new ArrayList<>();
    private List<Tag> tags = new ArrayList<>();

    public void createAllContact(int n, User user) {
        if (user != null) {
            Faker faker = new Faker();


            for (int i = 0; i < n; i++) {
                Contact newContact = new Contact();
                newContact.setFirstname(faker.name().firstName());
                newContact.setLastname(faker.name().lastName());
                newContact.setTel(faker.phoneNumber().phoneNumber());
                newContact.setEmail(faker.internet().emailAddress());
                newContact.setUser(user);

                this.contacts.add(contactService.add(newContact));
            }

            Iterable<Tag> tagsIterable = tagService.all();

            Iterator<Tag> iterator = tagsIterable.iterator();
            while (iterator.hasNext()) {
                tags.add(iterator.next());
            }

            for (int i = 0; i < n; i += 2) {
                Contact contactSrc = randomContact();
                Contact contactDest = randomContact();
                Relationship relationship = new Relationship(contactSrc,contactDest, this.randomTag());
                relationshipRepository.save(relationship);
            }
        }
    }

    public Contact randomContact() {
        Random rand = new Random();
        Contact randomElement = contacts.get(rand.nextInt(contacts.size()));
        contacts.remove(randomElement);

        return randomElement;
    }

    public Tag randomTag() {
        Random rand = new Random();
        Tag randomElement = tags.get(rand.nextInt(tags.size()));

        return randomElement;
    }
}
