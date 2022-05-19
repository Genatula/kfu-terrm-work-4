package ru.kpfu.itis.genatulin.termwork.services;

import org.springframework.security.core.Authentication;
import ru.kpfu.itis.genatulin.termwork.models.Meeting;

import java.util.List;

public interface MeetingService {
    boolean checkMeetingId(Integer meetingId, Authentication authentication);
    List<Meeting> getMeetings();
}
