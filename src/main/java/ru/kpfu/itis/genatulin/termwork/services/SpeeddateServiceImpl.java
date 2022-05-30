package ru.kpfu.itis.genatulin.termwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.genatulin.termwork.converters.TargetConverter;
import ru.kpfu.itis.genatulin.termwork.converters.TimeConverter;
import ru.kpfu.itis.genatulin.termwork.dto.CreateSpeeddateForm;
import ru.kpfu.itis.genatulin.termwork.dto.UpdateImageForm;
import ru.kpfu.itis.genatulin.termwork.dto.UpdateSpeeddateForm;
import ru.kpfu.itis.genatulin.termwork.exceptions.*;
import ru.kpfu.itis.genatulin.termwork.models.Meeting;
import ru.kpfu.itis.genatulin.termwork.models.Speeddate;
import ru.kpfu.itis.genatulin.termwork.models.Target;
import ru.kpfu.itis.genatulin.termwork.models.User;
import ru.kpfu.itis.genatulin.termwork.repositories.SpeeddateRepository;

import java.sql.Time;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class SpeeddateServiceImpl implements SpeeddateService {
    private final SpeeddateRepository speeddateRepository;
    private final TargetService targetService;
    private final StorageService storageService;
    private final TimeConverter timeConverter;
    private final TargetConverter targetConverter;
    private final UserService userService;

    @Autowired
    public SpeeddateServiceImpl(SpeeddateRepository speeddateRepository, TargetService targetService, StorageService storageService, TimeConverter timeConverter, TargetConverter targetConverter, UserService userService) {
        this.speeddateRepository = speeddateRepository;
        this.targetService = targetService;
        this.storageService = storageService;
        this.timeConverter = timeConverter;
        this.targetConverter = targetConverter;
        this.userService = userService;
    }

    @Override
    public boolean checkSpeeddateId(Integer speeddateId, Authentication authentication) {
        return authentication.isAuthenticated();
    }

    @Override
    public List<Speeddate> getSpeeddates() {
        List<Speeddate> result = speeddateRepository.findAll();
        if (result.size() < 5) {
            return result;
        }
        return result.subList(0, 5);
    }

    @Override
    public boolean checkIfExistsById(Long id) {
        return speeddateRepository.existsById(id);
    }

    @Override
    public Speeddate getSpeeddate(Long id) throws SpeeddateDoesNotExistException {
        if (!checkIfExistsById(id)) {
            throw new SpeeddateDoesNotExistException();
        }
        return speeddateRepository.getById(id);
    }

    @Override
    public void createSpeeddate(CreateSpeeddateForm form) throws EmptyFileException, IncorrectExtensionException {
        try {
            Speeddate speeddate = new Speeddate();
            Target target = targetConverter.convert(form.getTarget());
            String filename = storageService.uploadImage(form.getFile());

            speeddate.setCaption(form.getName());
            speeddate.setName(form.getName());
            speeddate.setCreationDate(new Date());
            speeddate.setDate(java.sql.Date.valueOf(form.getDate()));
            speeddate.setTime(timeConverter.convert(form.getTime()));
            speeddate.setDescription(form.getDescription());
            speeddate.setLocation(form.getLocation());
            speeddate.setShortDescription(form.getShortDescription());
            speeddate.setTarget(target);
            speeddate.setImage(storageService.getFileByName(filename));
            speeddate.setParticipants(Set.of(userService.getCurrentUser()));
            speeddateRepository.save(speeddate);
        } catch (FileDoesNotExistException e) {
            throw new IllegalStateException();
        }
    }

    @Override
    public void updateSpeeddate(UpdateSpeeddateForm form, Long id) {
        Speeddate speeddate = speeddateRepository.getById(id);

        speeddate.setTarget(targetService.getTarget(form.getTarget()));
        speeddate.setName(form.getName());
        speeddate.setDescription(form.getDescription());
        speeddate.setShortDescription(form.getShortDescription());
        speeddate.setLocation(form.getLocation());
        speeddate.setTime(Time.valueOf(form.getTime()));
        speeddate.setDate(java.sql.Date.valueOf(form.getDate()));
        speeddate.setCaption(form.getName());

        speeddateRepository.save(speeddate);
    }

    @Override
    public void updateImage(UpdateImageForm form, Long id) throws EmptyFileException {
        Speeddate speeddate = speeddateRepository.getById(id);
        MultipartFile file = form.getFile();

        storageService.updateImage(file, speeddate.getImage().getFilename());
    }

    @Override
    public void removeUserFromSpeeddate(long id) throws SpeeddateDoesNotExistException, UserDoesNoxExistException {
        User user = userService.getCurrentUser();
        if (!checkIfExistsById(id)) {
            throw new SpeeddateDoesNotExistException();
        }
        Speeddate speeddate = speeddateRepository.getById(id);
        Set<User> participants = speeddate.getParticipants();
        if (!participants.contains(user)) {
            throw new UserDoesNoxExistException();
        }
        participants.remove(user);
        speeddateRepository.save(speeddate);
    }

    @Override
    public void addUserToSpeeddate(long id) throws SpeeddateDoesNotExistException, UserAlreadyInEventException {
        User user = userService.getCurrentUser();
        if (!checkIfExistsById(id)) {
            throw new SpeeddateDoesNotExistException();
        }
        Speeddate speeddate = speeddateRepository.getById(id);
        Set<User> participants = speeddate.getParticipants();
        if (participants.contains(user)) {
            throw new UserAlreadyInEventException();
        }
        participants.add(user);
        speeddateRepository.save(speeddate);
    }
}
