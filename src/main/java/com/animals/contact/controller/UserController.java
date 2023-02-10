package com.animals.contact.controller;

import com.animals.contact.entity.User;
import com.animals.contact.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

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
        Optional<User> userProfile = userService.findUser(userEmail);

        if (userProfile.isPresent()) {
            model.addAttribute("user", userProfile);
            return "my-account";
        }

        return "redirect:/contact/list";
    }

    @GetMapping("/my-account/edit/{field}")
    public String displayMyAccountEdit(@PathVariable String field, Model model, Principal principal){
        String userEmail = principal.getName();
        Optional<User> userProfile = userService.findUser(userEmail);

        if (userProfile.isPresent()) {
            model.addAttribute("user", userProfile);

            model.addAttribute("field", field);
            return "my-account-edit";
        }

        return "redirect:/contact/list";
    }


    @PostMapping("/my-account/edit")
    public String updateUser(Principal principal, @RequestParam String field,  @RequestParam String value){

        String userEmail = principal.getName();
        Optional<User> user = userService.findUser(userEmail);

        if (user.isPresent()) {
            switch (field) {
                case "password":
                    user.get().setPassword(value);
                    break;
                case "firstname":
                    user.get().setFirstname(value);
                    break;
                case "lastname":
                    user.get().setLastname(value);
                    break;
                case "email":
                    user.get().setEmail(value);
                    break;
                case "avatar":
                    user.get().setAvatar(value);
                    break;
                default:
                    System.out.println("Erreur pas le bon champ !");
                    break;
            }

            userService.updateUser(user.get());
        }
        return "redirect:/my-account";
    }
    
}
