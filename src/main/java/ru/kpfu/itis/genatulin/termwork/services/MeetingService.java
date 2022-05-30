package ru.kpfu.itis.genatulin.termwork.services;

import org.springframework.security.core.Authentication;
import ru.kpfu.itis.genatulin.termwork.dto.CreateMeetingForm;
import ru.kpfu.itis.genatulin.termwork.dto.UpdateMeetingForm;
import ru.kpfu.itis.genatulin.termwork.exceptions.EmptyFileException;
import ru.kpfu.itis.genatulin.termwork.exceptions.IncorrectExtensionException;
import ru.kpfu.itis.genatulin.termwork.exceptions.MeetingDoesNotExistException;
import ru.kpfu.itis.genatulin.termwork.models.Meeting;

import java.util.List;

public interface MeetingService {
    boolean checkMeetingId(Integer meetingId, Authentication authentication);
    List<Meeting> getMeetings();
    boolean checkIfExistsById(Long id);
    Meeting getMeeting(Long id) throws MeetingDoesNotExistException;
    void createMeeting(CreateMeetingForm form) throws EmptyFileException, IncorrectExtensionException;
    void updateMeeting(UpdateMeetingForm form, Long id);
}
