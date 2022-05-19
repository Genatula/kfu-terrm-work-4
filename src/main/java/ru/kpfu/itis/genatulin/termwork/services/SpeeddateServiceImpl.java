package ru.kpfu.itis.genatulin.termwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.genatulin.termwork.models.Speeddate;
import ru.kpfu.itis.genatulin.termwork.repositories.SpeeddateRepository;

import java.util.List;

@Service
public class SpeeddateServiceImpl implements SpeeddateService {
    private final SpeeddateRepository speeddateRepository;

    @Autowired
    public SpeeddateServiceImpl(SpeeddateRepository speeddateRepository) {
        this.speeddateRepository = speeddateRepository;
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
}
