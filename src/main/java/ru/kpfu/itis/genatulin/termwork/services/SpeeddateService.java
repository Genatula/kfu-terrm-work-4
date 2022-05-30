package ru.kpfu.itis.genatulin.termwork.services;

import org.springframework.security.core.Authentication;
import ru.kpfu.itis.genatulin.termwork.dto.CreateSpeeddateForm;
import ru.kpfu.itis.genatulin.termwork.dto.UpdateImageForm;
import ru.kpfu.itis.genatulin.termwork.dto.UpdateSpeeddateForm;
import ru.kpfu.itis.genatulin.termwork.exceptions.EmptyFileException;
import ru.kpfu.itis.genatulin.termwork.exceptions.IncorrectExtensionException;
import ru.kpfu.itis.genatulin.termwork.exceptions.SpeeddateDoesNotExistException;
import ru.kpfu.itis.genatulin.termwork.models.Speeddate;

import java.util.List;

public interface SpeeddateService {
    boolean checkSpeeddateId(Integer speeddateId, Authentication authentication);
    List<Speeddate> getSpeeddates();
    boolean checkIfExistsById(Long id);
    Speeddate getSpeeddate(Long id) throws SpeeddateDoesNotExistException;
    void createSpeeddate(CreateSpeeddateForm form) throws EmptyFileException, IncorrectExtensionException;
    void updateSpeeddate(UpdateSpeeddateForm form, Long id);

    void updateImage(UpdateImageForm form, Long id) throws EmptyFileException;
}
