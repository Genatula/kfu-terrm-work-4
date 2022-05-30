package ru.kpfu.itis.genatulin.termwork.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.genatulin.termwork.models.Target;

@Component
public class TargetConverter implements Converter<String, Target> {
    @Override
    public Target convert(String source) {
        if (source.equals("partnership")) {
            return Target.PARTNERSHIP;
        }
        else if (source.equals("friendship")) {
            return Target.FRIENDSHIP;
        }
        else {
            throw new IllegalArgumentException();
        }
    }
}
