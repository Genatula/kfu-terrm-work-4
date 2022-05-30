package ru.kpfu.itis.genatulin.termwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.genatulin.termwork.dto.SignUpForm;
import ru.kpfu.itis.genatulin.termwork.exceptions.FileDoesNotExistException;
import ru.kpfu.itis.genatulin.termwork.exceptions.UserWithEmailAlreadyExistsException;
import ru.kpfu.itis.genatulin.termwork.exceptions.UserWithUsernameAlreadyExistsException;
import ru.kpfu.itis.genatulin.termwork.services.UserService;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping(value = "/register")
public class SignUpController {
    private final UserService userService;

    @Autowired
    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getPage(ModelMap modelMap, Authentication authentication) {
        if (authentication != null) {
            return "redirect:/feed";
        }
        SignUpForm form = new SignUpForm();
        modelMap.put("form", form);
        return "register";
    }

    @PostMapping
    public String register(@Valid @ModelAttribute("form") SignUpForm form, BindingResult bindingResult, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        try {
            userService.createUser(form);
            return "redirect:/feed";
        } catch (UserWithUsernameAlreadyExistsException e) {
            modelMap.addAttribute("username_error", true);
            return "register";
        } catch (UserWithEmailAlreadyExistsException e) {
            modelMap.addAttribute("email_error", true);
            return "register";
        } catch (FileDoesNotExistException | IOException e) {
            return "error";
        }
    }
}
