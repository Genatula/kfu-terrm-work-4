package ru.kpfu.itis.genatulin.termwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.genatulin.termwork.models.Meeting;
import ru.kpfu.itis.genatulin.termwork.services.MeetingService;

import java.util.List;

@Controller
@RequestMapping(value = "/meetings")
public class MeetingsController {
    private final MeetingService meetingService;

    @Autowired
    public MeetingsController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @GetMapping
    public String getMeetings(ModelMap modelMap) {
        List<Meeting> meetings = meetingService.getMeetings();
        modelMap.addAttribute("meetings", meetings);
        return "meetings";
    }
}
