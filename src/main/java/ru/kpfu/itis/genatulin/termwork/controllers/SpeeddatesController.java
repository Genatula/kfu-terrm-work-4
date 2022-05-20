package ru.kpfu.itis.genatulin.termwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.genatulin.termwork.models.Speeddate;
import ru.kpfu.itis.genatulin.termwork.services.SpeeddateService;

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
}
