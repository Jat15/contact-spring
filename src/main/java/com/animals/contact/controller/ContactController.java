package com.animals.contact.controller;

import com.animals.contact.entity.Contact;
import com.animals.contact.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/contact")
public class ContactController {

    @Autowired
    ContactRepository contactRepository;
    @GetMapping(path="/list")
    public String getAllContact(Model model) {
        Iterable<Contact> contacts = contactRepository.findAll();
        model.addAttribute("contacts", contacts);

        return "list-contact";
    }


}
