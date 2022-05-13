package ru.kpfu.itis.genatulin.termwork.services;

import org.springframework.security.core.Authentication;

public interface MeetingService {
    boolean checkMeetingId(Integer meetingId, Authentication authentication);
}
