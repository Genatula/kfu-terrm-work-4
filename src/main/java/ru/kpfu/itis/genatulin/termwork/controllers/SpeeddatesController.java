package ru.kpfu.itis.genatulin.termwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import ru.kpfu.itis.genatulin.termwork.dto.CreateSpeeddateForm;
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

    @GetMapping(value = {"/{id}", "/{id}/edit"})
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
    public String getCreateForm() {
        return "speeddate_create";
    }

    @PostMapping(value = "/create")
    public String createSpeeddate(@Valid CreateSpeeddateForm form, RedirectAttributesModelMap redirectAttributesModelMap, BindingResult result) {
        if (result.hasErrors()) {
            return "speeddate_create";
        }
        speeddateService.createSpeeddate(form);
        redirectAttributesModelMap.addAttribute("created", true);
        return "redirect:/speeddates";
    }
}
