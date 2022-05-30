package ru.kpfu.itis.genatulin.termwork.services;

import org.springframework.security.core.Authentication;
import ru.kpfu.itis.genatulin.termwork.dto.CreateMeetingForm;
import ru.kpfu.itis.genatulin.termwork.dto.UpdateImageForm;
import ru.kpfu.itis.genatulin.termwork.dto.UpdateMeetingForm;
import ru.kpfu.itis.genatulin.termwork.exceptions.*;
import ru.kpfu.itis.genatulin.termwork.models.Meeting;

import java.util.List;

public interface MeetingService {
    boolean checkMeetingId(Integer meetingId, Authentication authentication);
    List<Meeting> getMeetings();
    boolean checkIfExistsById(Long id);
    Meeting getMeeting(Long id) throws MeetingDoesNotExistException;
    void createMeeting(CreateMeetingForm form) throws EmptyFileException, IncorrectExtensionException;
    void updateMeeting(UpdateMeetingForm form, Long id);

    void updateImage(UpdateImageForm form, Long id) throws EmptyFileException;

    void addUserToMeeting(Long id) throws MeetingDoesNotExistException, UserAlreadyInEventException;

    void removeUserFromMeeting(Long id) throws MeetingDoesNotExistException, UserDoesNoxExistException;
}
