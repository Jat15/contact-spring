package com.animals.contact.controller;

import com.animals.contact.entity.Contact;
import com.animals.contact.entity.Relationship;
import com.animals.contact.entity.User;
import com.animals.contact.repository.ContactRepository;
import com.animals.contact.repository.UserRepository;
import com.animals.contact.service.RelationshipService;
import com.animals.contact.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping(path="/contact")
public class ContactController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private RelationshipService relationship;

    @GetMapping(path="/list")
    public String getAllContact(Model model, Principal principal) {
        String userEmail = principal.getName();
        Optional<User> user = userService.findUser(userEmail);


        if (user.isPresent()){
            model.addAttribute("user", user.get());
            model.addAttribute("contacts", user.get().getContacts());
            return "list-contact";
        }

        return "redirect:/home";
    }

    @GetMapping("/detail/{id}")
    public String displayContactDetail(@PathVariable Long id, Model model, Principal principal){
        String userEmail = principal.getName();
        Optional<User> userProfile = userService.findUser(userEmail);

        if (userProfile.isPresent()) {
            model.addAttribute("user", userProfile.get());
            Optional<Contact> contactOptional = contactRepository.findById(id);

            if(contactOptional.isPresent()){
                model.addAttribute("contact", contactOptional.get());

                Iterable<Relationship> relationsSrc = relationship.findByIdSrc(contactOptional.get().getId());
                model.addAttribute("relationsSrc", relationsSrc);

                Iterable<Relationship> relationsDest = relationship.findByIdDest(contactOptional.get().getId());
                model.addAttribute("relationsDest", relationsDest);

                return "detail";
            }
        }

        return "404";
    }

    @GetMapping("/add")
    public String displayFormAddContact(Model model, Principal principal){
        String userEmail = principal.getName();
        Optional<User> userProfile = userService.findUser(userEmail);

        if (userProfile.isPresent()) {
            model.addAttribute("user", userProfile.get());

            model.addAttribute("contact", new Contact());
            return "add-contact";
        }

        return "404";
    }

    @PostMapping("/add")
    public String addContact (Contact contact, Principal principal) {
        String userEmail = principal.getName();
        Optional<User> user = userService.findUser(userEmail);

        if (user.isPresent()) {
            contact.setUser(user.get());
            contactRepository.save(contact);
        }

        return "redirect:/contact/list";
    }
}
