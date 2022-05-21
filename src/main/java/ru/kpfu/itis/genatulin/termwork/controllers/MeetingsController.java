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
import ru.kpfu.itis.genatulin.termwork.dto.CreateMeetingForm;
import ru.kpfu.itis.genatulin.termwork.dto.UpdateMeetingForm;
import ru.kpfu.itis.genatulin.termwork.exceptions.MeetingDoesNotExistException;
import ru.kpfu.itis.genatulin.termwork.models.Meeting;
import ru.kpfu.itis.genatulin.termwork.services.MeetingService;

import javax.validation.Valid;
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

    @GetMapping(value ="/{id}")
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
    public String createMeeting(@Valid CreateMeetingForm form, RedirectAttributesModelMap redirectAttributesModelMap, BindingResult result) {
        if (result.hasErrors()) {
            return "meeting_create";
        }
        meetingService.createMeeting(form);
        redirectAttributesModelMap.addAttribute("created", true);
        return "redirect:/meetings";
    }

    @GetMapping(value = "/{id}/edit")
    public String getMeetingEditForm(ModelMap modelMap, @PathVariable(value = "id") String id) {
        try {
            Meeting meeting = meetingService.getMeeting(Long.valueOf(id));
            modelMap.addAttribute("meeting", meeting);
            return "meeting_edit";
        } catch (MeetingDoesNotExistException e) {
            return "404";
        }
    }
    @PostMapping(value = "/{id}/edit")
    public String updateArticle(@Valid UpdateMeetingForm form, @PathVariable(value = "id") String id, BindingResult result, RedirectAttributesModelMap redirectAttributesModelMap) {
        if (result.hasErrors()) {
            return "meeting_edit";
        }
        meetingService.updateMeeting(form, Long.valueOf(id));
        redirectAttributesModelMap.addAttribute("updated", true);
        return "redirect:/meetings/" + id;
    }
}
