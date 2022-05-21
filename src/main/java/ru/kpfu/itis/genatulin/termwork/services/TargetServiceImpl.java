package ru.kpfu.itis.genatulin.termwork.services;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.genatulin.termwork.models.Target;

import java.util.Locale;

@Service
public class TargetServiceImpl implements TargetService {
    @Override
    public Target getTarget(String targetName) {
        if (targetName.toLowerCase(Locale.ROOT).equals("friendship")) {
            return Target.FRIENDSHIP;
        }
        else if (targetName.toLowerCase(Locale.ROOT).equals("partnership")) {
            return Target.PARTNERSHIP;
        }
        else {
            throw new IllegalArgumentException();
        }
    }
}
