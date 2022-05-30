package ru.kpfu.itis.genatulin.termwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import ru.kpfu.itis.genatulin.termwork.dto.CreateSpeeddateForm;
import ru.kpfu.itis.genatulin.termwork.dto.UpdateImageForm;
import ru.kpfu.itis.genatulin.termwork.dto.UpdateSpeeddateForm;
import ru.kpfu.itis.genatulin.termwork.exceptions.EmptyFileException;
import ru.kpfu.itis.genatulin.termwork.exceptions.IncorrectExtensionException;
import ru.kpfu.itis.genatulin.termwork.exceptions.SpeeddateDoesNotExistException;
import ru.kpfu.itis.genatulin.termwork.models.Speeddate;
import ru.kpfu.itis.genatulin.termwork.services.SpeeddateService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/speeddates")
public class SpeeddatesController {
    private final SpeeddateService speeddateService;

    @Autowired
    public SpeeddatesController(SpeeddateService speeddateService) {
        this.speeddateService = speeddateService;
    }

    @GetMapping
    public String getSpeeddates(ModelMap modelMap, HttpServletRequest request) {
        List<Speeddate> speeddates = speeddateService.getSpeeddates();
        Boolean isAdmin = request.isUserInRole("ROLE_ADMIN");
        modelMap.addAttribute("speeddates", speeddates);
        modelMap.addAttribute("is_admin", isAdmin);
        return "speeddates";
    }

    @GetMapping(value ="/{id}")
    public String getSpeeddate(ModelMap modelMap, @PathVariable(value = "id") String id, HttpServletRequest request) {
        try {
            Speeddate speeddate = speeddateService.getSpeeddate(Long.valueOf(id));
            Boolean isAdmin = request.isUserInRole("ROLE_ADMIN");
            modelMap.addAttribute("is_admin", isAdmin);
            modelMap.addAttribute("speeddate", speeddate);
            return "speeddate";
        } catch (SpeeddateDoesNotExistException e) {
            return "404";
        }
    }

    @GetMapping(value = "/create")
    public String getCreateForm(ModelMap modelMap) {
        modelMap.addAttribute("form", new CreateSpeeddateForm());
        return "speeddate_create";
    }

    @PostMapping(value = "/create")
    public String createSpeeddate(@Valid @ModelAttribute("form") CreateSpeeddateForm form, BindingResult result, RedirectAttributesModelMap redirectAttributesModelMap, ModelMap modelMap) {
        if (result.hasErrors()) {
            return "speeddate_create";
        }
        try {
            speeddateService.createSpeeddate(form);
        } catch (EmptyFileException e) {
            modelMap.addAttribute("empty_file", true);
            return "speeddate_create";
        } catch (IncorrectExtensionException e) {
            modelMap.addAttribute("incorrect_extension", true);
            return "speeddate_create";
        }
        redirectAttributesModelMap.addAttribute("created", true);
        return "redirect:/speeddates";
    }

    @GetMapping(value = "/{id}/edit")
    public String getSpeeddateEditForm(ModelMap modelMap, @PathVariable(value = "id") String id) {
        try {
            Speeddate speeddate = speeddateService.getSpeeddate(Long.valueOf(id));
            UpdateSpeeddateForm form = new UpdateSpeeddateForm();
            form.setDate(speeddate.getDate().toString());
            form.setDescription(speeddate.getDescription());
            form.setShortDescription(speeddate.getDescription());
            form.setLocation(speeddate.getLocation());
            form.setName(speeddate.getName());
            form.setTime(speeddate.getTime().toString());
            form.setTarget(speeddate.getTarget().getValue());

            modelMap.addAttribute("speeddate", speeddate);
            modelMap.addAttribute("form", form);
            return "speeddate_edit";
        } catch (SpeeddateDoesNotExistException e) {
            return "404";
        }
    }

    @PostMapping(value = "/{id}/edit")
    public String updateSpeeddate(@Valid @ModelAttribute("form") UpdateSpeeddateForm form, BindingResult result, @PathVariable(value = "id") String id, RedirectAttributesModelMap redirectAttributesModelMap, ModelMap modelMap) {
        if (result.hasErrors()) {
            try {
                Speeddate speeddate = speeddateService.getSpeeddate(Long.valueOf(id));
                modelMap.addAttribute("speeddate", speeddate);
                return "speeddate_edit";
            } catch (SpeeddateDoesNotExistException e) {
                return "404";
            }
        }
        speeddateService.updateSpeeddate(form, Long.valueOf(id));
        redirectAttributesModelMap.addAttribute("updated", true);
        return "redirect:/speeddates/" + id;
    }

    @GetMapping(value = "/{id}/edit/image")
    public String getUpdateImageForm(@PathVariable String id, ModelMap modelMap) {
        try {
            Speeddate speeddate = speeddateService.getSpeeddate(Long.valueOf(id));
            UpdateImageForm form = new UpdateImageForm();
            modelMap.addAttribute("speeddate", speeddate);
            modelMap.addAttribute("form", form);
            return "speeddate_edit_image";
        } catch (SpeeddateDoesNotExistException e) {
            return "404";
        }
    }

    @PostMapping(value = "/{id}/edit/image")
    public String updateImage(@Valid @ModelAttribute("form") UpdateImageForm form, BindingResult result, @PathVariable String id, ModelMap modelMap) {
        Speeddate speeddate = null;
        try {
            speeddate = speeddateService.getSpeeddate(Long.valueOf(id));
            if (result.hasErrors()) {
                modelMap.addAttribute("speeddate", speeddate);
                return "speeddate_edit_image";
            }
            speeddateService.updateImage(form, Long.valueOf(id));
            return "redirect:/speeddates/" + id + "/edit";
        } catch (SpeeddateDoesNotExistException e) {
            return "404";
        } catch (EmptyFileException e) {
            modelMap.addAttribute("speeddate", speeddate);
            modelMap.addAttribute("empty_file", true);
            return "speeddate_edit_image";
        }
    }
}
