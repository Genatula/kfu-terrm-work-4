package ru.kpfu.itis.genatulin.termwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.genatulin.termwork.converters.TimeConverter;
import ru.kpfu.itis.genatulin.termwork.dto.CreateMeetingForm;
import ru.kpfu.itis.genatulin.termwork.dto.UpdateImageForm;
import ru.kpfu.itis.genatulin.termwork.dto.UpdateMeetingForm;
import ru.kpfu.itis.genatulin.termwork.exceptions.*;
import ru.kpfu.itis.genatulin.termwork.models.Meeting;
import ru.kpfu.itis.genatulin.termwork.models.User;
import ru.kpfu.itis.genatulin.termwork.repositories.MeetingRepository;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class MeetingServiceImpl implements MeetingService {
    private final MeetingRepository meetingRepository;
    private final StorageService storageService;
    private final TimeConverter timeConverter;
    private final UserService userService;

    @Autowired
    public MeetingServiceImpl(MeetingRepository meetingRepository, StorageService storageService, TimeConverter timeConverter, UserService userService) {
        this.meetingRepository = meetingRepository;
        this.storageService = storageService;
        this.timeConverter = timeConverter;
        this.userService = userService;
    }

    @Override
    public boolean checkMeetingId(Integer meetingId, Authentication authentication) {
        return authentication.isAuthenticated();
    }

    @Override
    public List<Meeting> getMeetings() {
        List<Meeting> result = meetingRepository.findAll();
        if (result.size() < 5) {
            return result;
        }
        return result.subList(0, 5);
    }

    @Override
    public boolean checkIfExistsById(Long id) {
        return meetingRepository.existsById(id);
    }

    @Override
    public Meeting getMeeting(Long id) throws MeetingDoesNotExistException {
        if (!checkIfExistsById(id)) {
            throw new MeetingDoesNotExistException();
        }
        return meetingRepository.getById(id);
    }

    @Override
    public void createMeeting(CreateMeetingForm form) throws EmptyFileException, IncorrectExtensionException {
        try {
            Meeting meeting = new Meeting();
            String filename = storageService.uploadImage(form.getFile());

            meeting.setCaption(form.getName());
            meeting.setName(form.getName());
            meeting.setCreationDate(new Date());
            meeting.setDate(java.sql.Date.valueOf(form.getDate()));
            meeting.setTime(timeConverter.convert(form.getTime()));
            meeting.setLocation(form.getLocation());
            meeting.setDescription(form.getDescription());
            meeting.setShortDescription(form.getShortDescription());
            meeting.setImage(storageService.getFileByName(filename));
            meeting.setParticipants(Set.of(userService.getCurrentUser()));

            meetingRepository.save(meeting);
        } catch (FileDoesNotExistException e) {
            throw new IllegalStateException();
        }
    }

    @Override
    public void updateMeeting(UpdateMeetingForm form, Long id) {
        Meeting meeting = meetingRepository.getById(id);

        meeting.setShortDescription(form.getShortDescription());
        meeting.setLocation(form.getLocation());
        meeting.setDescription(form.getDescription());
        meeting.setTime(Time.valueOf(form.getTime()));
        meeting.setDate(java.sql.Date.valueOf(form.getDate()));
        meeting.setCaption(form.getName());
        meeting.setName(form.getName());

        meetingRepository.save(meeting);
    }

    @Override
    public void updateImage(UpdateImageForm form, Long id) throws EmptyFileException {
        Meeting meeting = meetingRepository.getById(id);
        MultipartFile file = form.getFile();

        storageService.updateImage(file, meeting.getImage().getFilename());
    }

    @Override
    public void addUserToMeeting(Long id) throws MeetingDoesNotExistException, UserAlreadyInEventException {
        User user = userService.getCurrentUser();
        if (!checkIfExistsById(id)) {
            throw new MeetingDoesNotExistException();
        }
        Meeting meeting = meetingRepository.getById(id);
        Set<User> participants = meeting.getParticipants();
        if (participants.contains(user)) {
            throw new UserAlreadyInEventException();
        }
        participants.add(user);
        meetingRepository.save(meeting);
    }

    @Override
    public void removeUserFromMeeting(Long id) throws MeetingDoesNotExistException, UserDoesNoxExistException {
        User user = userService.getCurrentUser();
        if (!checkIfExistsById(id)) {
            throw new MeetingDoesNotExistException();
        }
        Meeting meeting = meetingRepository.getById(id);
        Set<User> participants = meeting.getParticipants();
        if (!participants.contains(user)) {
            throw new UserDoesNoxExistException();
        }
        participants.remove(user);
        meetingRepository.save(meeting);
    }
}
