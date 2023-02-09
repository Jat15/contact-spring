package com.animals.contact.controller;

import com.animals.contact.entity.User;
import com.animals.contact.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping(path="/contact")
public class ContactController {

    private Long idEmulate = 1l;

    @Autowired
    UserRepository userRepository;
    @GetMapping(path="/list")
    public String getAllContact(Model model) {
        Optional<User> user = userRepository.findById(idEmulate);

        if (user.isPresent()){
            model.addAttribute("contacts", user.get().getContacts());
            return "list-contact";
        }

        return "redirect:/home";
    }


}
