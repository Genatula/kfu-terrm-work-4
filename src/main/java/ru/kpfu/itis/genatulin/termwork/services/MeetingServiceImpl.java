package ru.kpfu.itis.genatulin.termwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.genatulin.termwork.models.Meeting;
import ru.kpfu.itis.genatulin.termwork.repositories.MeetingRepository;

import java.util.List;

@Service
public class MeetingServiceImpl implements MeetingService {
    private final MeetingRepository meetingRepository;

    @Autowired
    public MeetingServiceImpl(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
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
}
