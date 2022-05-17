package ru.kpfu.itis.genatulin.termwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.genatulin.termwork.dto.SignUpForm;
import ru.kpfu.itis.genatulin.termwork.exceptions.UserWithEmailAlreadyExistsException;
import ru.kpfu.itis.genatulin.termwork.exceptions.UserWithUsernameAlreadyExistsException;
import ru.kpfu.itis.genatulin.termwork.services.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/register")
public class SignUpController {
    private final UserService userService;

    @Autowired
    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getPage() {
        return "register";
    }

    @PostMapping
    public String register(@Valid SignUpForm form, BindingResult bindingResult, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        try {
            userService.createUser(form);
            return "redirect:/feed";
        } catch (UserWithUsernameAlreadyExistsException e) {
            modelMap.addAttribute("username_error_message", "User with such username already exists");
            return "register";
        } catch (UserWithEmailAlreadyExistsException e) {
            modelMap.addAttribute("email_error_message", "User with such email already exists");
            return "register";
        }
    }
}
