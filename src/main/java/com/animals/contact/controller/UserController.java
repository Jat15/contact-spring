package com.animals.contact.controller;

import com.animals.contact.entity.User;
import com.animals.contact.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/sign-up")
    public String displayFormAddUser(Model model){
        model.addAttribute("user", new User());
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String addUser (User user) {
        userService.addUser(user);
        return "redirect:home";
    }

    @GetMapping("/my-account")
    public String displayMyAccount(Model model, Principal principal){

        String userEmail = principal.getName();
        User user = userService.findUser(userEmail);

        model.addAttribute("user", user);

        return "my-account";
    }

    @GetMapping("/my-account/edit/{field}")
    public String displayMyAccountEdit(@PathVariable String field, Model model){
        model.addAttribute("field", field);
        return "my-account-edit";
    }


    @PostMapping("/my-account/edit")
    public String updateUser(Principal principal, @RequestParam String field,  @RequestParam String value){

        String userEmail = principal.getName();
        User user = userService.findUser(userEmail);

        switch(field){
            case "password":
                user.setPassword(value);
                break;
            case "firstname":
                user.setFirstname(value);
                break;
            case "lastname":
                user.setLastname(value);
                break;
            case "email":
                user.setEmail(value);
                break;
            case "avatar":
                user.setAvatar(value);
                break;
            default:
                System.out.println("Erreur pas le bon champ !");
                break;
        }

        userService.updateUser(user);

        return "redirect:/my-account";
    }
    
}
