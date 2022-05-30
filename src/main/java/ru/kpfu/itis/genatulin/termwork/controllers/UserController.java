package ru.kpfu.itis.genatulin.termwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import ru.kpfu.itis.genatulin.termwork.dto.UpdateForm;
import ru.kpfu.itis.genatulin.termwork.dto.UpdateImageForm;
import ru.kpfu.itis.genatulin.termwork.dto.UpdatePasswordForm;
import ru.kpfu.itis.genatulin.termwork.exceptions.*;
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
    public String getUserPage(ModelMap modelMap) {
        User user = userService.getCurrentUser();
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("filename", user.getProfileImage().getFilename() + user.getProfileImage().getExtension());
        return "user";
    }

    @GetMapping(value = "/edit")
    public String getUserEditPage(Principal principal, ModelMap modelMap) throws UserDoesNoxExistException {
        User user = userService.getUserByUsername(principal.getName());
        UpdateForm form = new UpdateForm();
        form.setEmail(user.getEmail());
        form.setFirstname(user.getFirstname());
        form.setUsername(user.getUsername());

        modelMap.addAttribute("user", user);
        modelMap.addAttribute("form", form);
        return "user_edit";
    }

    @PostMapping(value = "/edit")
    public String updateUserInformation(@Valid @ModelAttribute("form") UpdateForm form, BindingResult result, ModelMap modelMap, Principal principal, RedirectAttributesModelMap redirectAttributesModelMap) {
        if (result.hasErrors()) {
            return "user_edit";
        }
        try {
            userService.updateUser(form, principal.getName());
            redirectAttributesModelMap.addAttribute("changed", true);
            return "redirect:/user";
        } catch (UserWithUsernameAlreadyExistsException e) {
            modelMap.addAttribute("username_error", true);
            return "user_edit";
        } catch (UserWithEmailAlreadyExistsException e) {
            modelMap.addAttribute("email_error", true);
            return "user_edit";
        }
    }

    @GetMapping(value = "/edit/password")
    public String getEditPasswordForm(ModelMap modelMap) {
        modelMap.addAttribute("form", new UpdatePasswordForm());
        return "user_password";
    }

    @PostMapping(value = "/edit/password")
    public String editPassword(@Valid @ModelAttribute("form") UpdatePasswordForm form, BindingResult result, ModelMap modelMap) {
        if (result.hasErrors()) {
            return "user_password";
        }
        try {
            userService.updatePassword(form);
            return "redirect:/user";
        } catch (IncorrectPasswordException e) {
            modelMap.addAttribute("old_password_error", true);
            return "user_password";
        }
    }

    @GetMapping(value = "/edit/image")
    public String getEditPhotoForm(ModelMap modelMap) {
        UpdateImageForm form = new UpdateImageForm();
        modelMap.addAttribute("form", form);
        return "user_edit_image";
    }

    @PostMapping(value = "/edit/image")
    public String updatePhoto(@Valid @ModelAttribute("form") UpdateImageForm form, BindingResult result, ModelMap modelMap) {
        if (result.hasErrors()) {
            return "user_edit_image";
        }
        try {
            userService.updateImage(form);
            return "redirect:/user";
        } catch (EmptyFileException e) {
            modelMap.addAttribute("empty_file", true);
            return "user_edit_image";
        }
    }
}
