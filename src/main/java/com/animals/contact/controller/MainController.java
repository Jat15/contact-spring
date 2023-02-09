package com.animals.contact.controller;

import com.animals.contact.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

  @GetMapping("/")
  public String displayHome(){
      return "home";
  }

}
