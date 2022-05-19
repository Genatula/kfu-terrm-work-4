package ru.kpfu.itis.genatulin.termwork.services;

import org.springframework.security.core.Authentication;
import ru.kpfu.itis.genatulin.termwork.models.Speeddate;

import java.util.List;

public interface SpeeddateService {
    boolean checkSpeeddateId(Integer speeddateId, Authentication authentication);
    List<Speeddate> getSpeeddates();
}
