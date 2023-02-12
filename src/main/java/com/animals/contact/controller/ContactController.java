package com.animals.contact.controller;

import com.animals.contact.entity.Contact;
import com.animals.contact.entity.Relationship;
import com.animals.contact.entity.User;
import com.animals.contact.repository.ContactRepository;
import com.animals.contact.service.ContactService;
import com.animals.contact.service.RelationshipService;
import com.animals.contact.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping(path="/contact")
public class ContactController {
    @Autowired
    private UserService userService;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private RelationshipService relationship;
    @Autowired
    private ContactService contactService;

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

    @GetMapping("/detail/edit/{id}/{field}")
    public String displayMyAccount(Model model, Principal principal, @PathVariable Long id, @PathVariable String field){
        String userEmail = principal.getName();
        Optional<User> userProfile = userService.findUser(userEmail);

        if (userProfile.isPresent()) {
            model.addAttribute("user", userProfile.get());
            model.addAttribute("contactId", id);
            model.addAttribute("field", field);
        }

        return "detail-edit";
    }

    @PostMapping("/detail/edit")
    public String updateUser(@RequestParam String field, @RequestParam String value, @RequestParam Long contactId){

        Optional<Contact> contact= contactRepository.findById(contactId);

        if (contact.isPresent()) {
            switch (field) {
                case "firstname":
                    contact.get().setFirstname(value);
                    break;
                case "lastname":
                    contact.get().setLastname(value);
                    break;
                case "email":
                    contact.get().setEmail(value);
                    break;
                case "tel":
                    contact.get().setTel(value);
                    break;
                default:
                    System.out.println("Erreur pas le bon champ !");
                    break;
            }

            contactRepository.save(contact.get());

        }
        return "redirect:/contact/detail/" + contactId;
    }

    @GetMapping("/delete/{id}")
    public String deleteContact(Principal principal, @PathVariable Long id) {
        String userEmail = principal.getName();
        Optional<User> user = userService.findUser(userEmail);

        if (user.isPresent()) {
            contactService.delete(id, user.get());
        }

        return "redirect:/contact/list";
    }

    @GetMapping("/find")
    public String displaySearch(Principal principal, @RequestParam String search, Model model) {
        String userEmail = principal.getName();
        Optional<User> user = userService.findUser(userEmail);


        if (user.isPresent()){
            Iterable<Contact> contacts = contactService.findByString(search);

            model.addAttribute("user", user.get());
            model.addAttribute("contacts", contacts);
            return "list-contact";
        }

        return "redirect:/home";
    }

}
