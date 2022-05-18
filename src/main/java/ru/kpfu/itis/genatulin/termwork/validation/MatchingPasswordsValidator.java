package ru.kpfu.itis.genatulin.termwork.validation;

import ru.kpfu.itis.genatulin.termwork.dto.HavingMatchingPasswords;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MatchingPasswordsValidator implements ConstraintValidator<MatchingPasswords, Object> {
    @Override
    public boolean isValid(Object s, ConstraintValidatorContext constraintValidatorContext) {
        HavingMatchingPasswords form = (HavingMatchingPasswords) s;
        return form.getPassword().equals(form.getMatchingPassword());
    }
}
