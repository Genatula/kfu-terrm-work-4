package ru.kpfu.itis.genatulin.termwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import ru.kpfu.itis.genatulin.termwork.dto.CreateMeetingForm;
import ru.kpfu.itis.genatulin.termwork.dto.UpdateMeetingForm;
import ru.kpfu.itis.genatulin.termwork.exceptions.EmptyFileException;
import ru.kpfu.itis.genatulin.termwork.exceptions.IncorrectExtensionException;
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
    public String getCreateForm(ModelMap modelMap) {
        modelMap.addAttribute("form", new CreateMeetingForm());
        return "meeting_create";
    }

    @PostMapping(value = "/create")
    public String createMeeting(@Valid @ModelAttribute("form") CreateMeetingForm form, BindingResult result, RedirectAttributesModelMap redirectAttributesModelMap, ModelMap modelMap) {
        if (result.hasErrors()) {
            return "meeting_create";
        }
        try {
            meetingService.createMeeting(form);
        } catch (EmptyFileException e) {
            modelMap.addAttribute("empty_file", true);
            return "meeting_create";
        } catch (IncorrectExtensionException e) {
            modelMap.addAttribute("incorrect_extension", true);
            return "meeting_create";
        }
        redirectAttributesModelMap.addAttribute("created", true);
        return "redirect:/meetings";
    }

    @GetMapping(value = "/{id}/edit")
    public String getMeetingEditForm(ModelMap modelMap, @PathVariable(value = "id") String id) {
        try {
            Meeting meeting = meetingService.getMeeting(Long.valueOf(id));
            UpdateMeetingForm form = new UpdateMeetingForm();
            form.setDate(meeting.getDate().toString());
            form.setDescription(meeting.getDescription());
            form.setShortDescription(meeting.getShortDescription());
            form.setLocation(meeting.getLocation());
            form.setName(meeting.getName());
            form.setTime(meeting.getTime().toString());

            modelMap.addAttribute("meeting", meeting);
            modelMap.addAttribute("form", form);
            return "meeting_edit";
        } catch (MeetingDoesNotExistException e) {
            return "404";
        }
    }
    @PostMapping(value = "/{id}/edit")
    public String updateArticle(@Valid @ModelAttribute("form") UpdateMeetingForm form, BindingResult result, @PathVariable(value = "id") String id, RedirectAttributesModelMap redirectAttributesModelMap, ModelMap modelMap) {
        if (result.hasErrors()) {
            try {
                Meeting meeting = meetingService.getMeeting(Long.valueOf(id));
                modelMap.addAttribute("meeting", meeting);
                return "meeting_edit";
            } catch (MeetingDoesNotExistException e) {
                return "404";
            }
        }
        meetingService.updateMeeting(form, Long.valueOf(id));
        redirectAttributesModelMap.addAttribute("updated", true);
        return "redirect:/meetings/" + id;
    }
}
