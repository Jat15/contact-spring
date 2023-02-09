package com.animals.contact.controller;

import com.animals.contact.entity.Contact;
import com.animals.contact.entity.User;
import com.animals.contact.repository.ContactRepository;
import com.animals.contact.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping(path="/contact")
public class ContactController {

    private Long idEmulate = 1l;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping(path="/list")
    public String getAllContact(Model model) {
        Optional<User> user = userRepository.findById(idEmulate);

        if (user.isPresent()){
            model.addAttribute("contacts", user.get().getContacts());
            return "list-contact";
        }

        return "redirect:/home";
    }

    @GetMapping("/contact/detail/{id}")
    public String displayContactDetail(@PathVariable Long id, Model model){
        Optional<Contact> contactOptional = contactRepository.findById(id);
        if(contactOptional.isPresent()){
            model.addAttribute("contact", contactOptional.get());
            return "detail.html";
        }else {
            return "404.html";
        }
    }

    @GetMapping("/add")
    public String displayFormAddContact(Model model){
        model.addAttribute("contact", new Contact());
        return "add-contact";
    }

    @PostMapping("/add")
    public String addContact (Contact contact) {
        Optional<User> user = userRepository.findById(idEmulate);

        if (user.isPresent()) {
            contact.setUser(user.get());
            contactRepository.save(contact);
        }

        return "redirect:/contact/list";
    }


}
