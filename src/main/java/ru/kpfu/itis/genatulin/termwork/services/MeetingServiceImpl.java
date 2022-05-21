package ru.kpfu.itis.genatulin.termwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.genatulin.termwork.dto.CreateMeetingForm;
import ru.kpfu.itis.genatulin.termwork.exceptions.MeetingDoesNotExistException;
import ru.kpfu.itis.genatulin.termwork.models.Meeting;
import ru.kpfu.itis.genatulin.termwork.repositories.MeetingRepository;

import java.sql.Time;
import java.util.Date;
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
    public void createMeeting(CreateMeetingForm form) {
        Meeting meeting = new Meeting();

        meeting.setCaption(form.getName());
        meeting.setName(form.getName());
        meeting.setCreationDate(new Date());
        meeting.setDate(java.sql.Date.valueOf(form.getDate()));
        meeting.setTime(Time.valueOf(form.getTime()));
        meeting.setLocation(form.getLocation());
        meeting.setDescription(form.getDescription());
        meeting.setShortDescription(form.getShortDescription());

        meetingRepository.save(meeting);
    }
}
