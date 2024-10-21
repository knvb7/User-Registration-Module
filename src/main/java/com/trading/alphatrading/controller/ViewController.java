package com.trading.alphatrading.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("")
public class ViewController {
    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("SignIn");
    }

    @GetMapping("/dashboard")
    public ModelAndView dashboard(@AuthenticationPrincipal OidcUser oidcUser, Model model) {
        model.addAttribute("name", oidcUser.getFullName());
        model.addAttribute("email", oidcUser.getEmail());
        return new ModelAndView("dashboard");  // Ensure this maps to a template (e.g., dashboard.html in Thymeleaf)
    }

    @GetMapping("/error")
    public ModelAndView handleError() {
        return new ModelAndView("Error");
    }

    @GetMapping("/user")
    public Principal user(Principal user){
        return user;
    }
}
