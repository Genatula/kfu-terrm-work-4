package ru.kpfu.itis.genatulin.termwork.services;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class SpeeddateServiceImpl implements SpeeddateService {
    @Override
    public boolean checkSpeeddateId(Integer speeddateId, Authentication authentication) {
        if (authentication.isAuthenticated()) {
            return true;
        }
        return false;
    }
}
