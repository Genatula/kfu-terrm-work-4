package ru.kpfu.itis.genatulin.termwork.services;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class MeetingServiceImpl implements MeetingService {
    @Override
    public boolean checkMeetingId(Integer meetingId, Authentication authentication) {
        if (authentication.isAuthenticated()) {
            return true;
        }
        return false;
    }
}
