package ru.kpfu.itis.genatulin.termwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import ru.kpfu.itis.genatulin.termwork.dto.CreateSpeeddateForm;
import ru.kpfu.itis.genatulin.termwork.dto.UpdateSpeeddateForm;
import ru.kpfu.itis.genatulin.termwork.exceptions.EmptyFileException;
import ru.kpfu.itis.genatulin.termwork.exceptions.IncorrectExtensionException;
import ru.kpfu.itis.genatulin.termwork.exceptions.SpeeddateDoesNotExistException;
import ru.kpfu.itis.genatulin.termwork.models.Speeddate;
import ru.kpfu.itis.genatulin.termwork.services.SpeeddateService;

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
    public String getSpeeddates(ModelMap modelMap) {
        List<Speeddate> speeddates = speeddateService.getSpeeddates();
        modelMap.addAttribute("speeddates", speeddates);
        return "speeddates";
    }

    @GetMapping(value ="/{id}")
    public String getSpeeddate(ModelMap modelMap, @PathVariable(value = "id") String id) {
        try {
            Speeddate speeddate = speeddateService.getSpeeddate(Long.valueOf(id));
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
}
