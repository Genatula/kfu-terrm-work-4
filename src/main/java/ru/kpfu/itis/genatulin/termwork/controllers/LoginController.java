package ru.kpfu.itis.genatulin.termwork.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
    @GetMapping
    public String getLoginPage(Authentication authentication) {
        if (authentication != null) {
            return "redirect:/feed";
        }
        return "login";
    }
}
