package com.animals.contact.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

  @GetMapping(value={
          "/",
          "/login"
  })
  public String displayHome(){
      return "home";
  }


}
