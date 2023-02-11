package com.animals.contact.controller;

import com.animals.contact.entity.Contact;
import com.animals.contact.entity.Tag;
import com.animals.contact.entity.User;
import com.animals.contact.service.ContactService;
import com.animals.contact.service.RelationshipService;
import com.animals.contact.service.TagService;
import com.animals.contact.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping(path="/relation")
public class RelationshipController {
    @Autowired
    private RelationshipService relationshipService;
    @Autowired
    private TagService tagService;
    @Autowired
    private ContactService contactService;
    @Autowired
    private UserService userService;

    @GetMapping("/add/{id}")
    public String displayFormAddRelationship(Principal principal, Model model, @PathVariable Long id){
        String userEmail = principal.getName();
        Optional<User> userProfile = userService.findUser(userEmail);

        if (userProfile.isPresent()) {
            model.addAttribute("user", userProfile.get());
        }

        Optional<Contact> contactSrc= contactService.findById(id);

        model.addAttribute("contactSrc", contactSrc.get());

        Iterable<Tag> tags = tagService.all();
        model.addAttribute("tags", tags);

        Iterable<Contact> contacts = contactService.all();
        model.addAttribute("contacts", contacts);


        return "add-relationship";
    }

    @PostMapping("/add")
    public String addRelationship( @RequestParam Long contactSrcId ,  @RequestParam Long tagSrcId, @RequestParam Long contactDestId){
        relationshipService.add(contactSrcId,contactDestId,tagSrcId);

        return "redirect:/contact/detail/" + contactSrcId;
    }
}
