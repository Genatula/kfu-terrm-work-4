package ru.kpfu.itis.genatulin.termwork.converters;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.sql.Time;

@Component
public class TimeConverter implements Converter<String, Time> {
    @Override
    public Time convert(String source) {
        StringBuilder result = new StringBuilder(source);
        while (result.toString().split(":").length < 3) {
            result.append(":00");
        }
        return Time.valueOf(result.toString());
    }
}
