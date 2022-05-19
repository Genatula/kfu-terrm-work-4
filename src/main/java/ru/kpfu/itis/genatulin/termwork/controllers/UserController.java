package ru.kpfu.itis.genatulin.termwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.genatulin.termwork.dto.UpdateForm;
import ru.kpfu.itis.genatulin.termwork.exceptions.UserWithEmailAlreadyExistsException;
import ru.kpfu.itis.genatulin.termwork.exceptions.UserWithUsernameAlreadyExistsException;
import ru.kpfu.itis.genatulin.termwork.models.User;
import ru.kpfu.itis.genatulin.termwork.security.details.UserDetailsImpl;
import ru.kpfu.itis.genatulin.termwork.services.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUserPage(Principal principal, ModelMap modelMap) {
        User user = ((UserDetailsImpl) principal).getUser();
        modelMap.addAttribute("user", user);
        return "user";
    }

    @GetMapping(value = "/edit")
    public String getUserEditPage(Principal principal, ModelMap modelMap) {
        User user = ((UserDetailsImpl) principal).getUser();
        modelMap.addAttribute("user", user);
        return "user_edit";
    }

    @PostMapping(value = "/edit")
    public String updateUserInformation(@Valid UpdateForm form, BindingResult result, ModelMap modelMap, Principal principal) {
        if (result.hasErrors()) {
            return "user_edit";
        }
        try {
            userService.updateUser(form, principal.getName());
            return "redirect:/user?changed=1";
        } catch (UserWithUsernameAlreadyExistsException e) {
            modelMap.addAttribute("username_error", true);
            return "user_edit";
        } catch (UserWithEmailAlreadyExistsException e) {
            modelMap.addAttribute("email_error", true);
            return "user_edit";
        }
    }
}
