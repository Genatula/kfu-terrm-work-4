package ru.kpfu.itis.genatulin.termwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import ru.kpfu.itis.genatulin.termwork.dto.CreateMeetingForm;
import ru.kpfu.itis.genatulin.termwork.exceptions.MeetingDoesNotExistException;
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

    @GetMapping(value = {"/{id}", "/{id}/edit"})
    public String getMeeting(ModelMap modelMap, @PathVariable(value = "id") String id) {
        try {
            Meeting meeting = meetingService.getMeeting(Long.valueOf(id));
            modelMap.addAttribute("meeting", meeting);
            return "meeting";
        } catch (MeetingDoesNotExistException e) {
            return "404";
        }
    }

    @GetMapping(value = "/create")
    public String getCreateForm() {
        return "meeting_create";
    }

    @PostMapping(value = "/create")
    public String createMeeting(CreateMeetingForm form, RedirectAttributesModelMap redirectAttributesModelMap) {
        meetingService.createMeeting(form);
        redirectAttributesModelMap.addAttribute("created", true);
        return "redirect:/meetings";
    }
}
