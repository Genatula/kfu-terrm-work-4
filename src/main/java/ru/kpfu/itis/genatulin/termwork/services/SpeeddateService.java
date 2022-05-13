package ru.kpfu.itis.genatulin.termwork.services;

import org.springframework.security.core.Authentication;

public interface SpeeddateService {
    boolean checkSpeeddateId(Integer speeddateId, Authentication authentication);
}
