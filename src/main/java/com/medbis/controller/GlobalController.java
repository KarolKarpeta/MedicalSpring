package com.medbis.controller;


import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice(basePackages = "com.medbis.controller")
public class GlobalController {

@ModelAttribute
    public void addLoggedUserAttribute(Model model, Authentication auth){

    /*   String name = auth.getName();
      model.addAttribute("name", name); */
    model.addAttribute("authentication", auth);
}

}
