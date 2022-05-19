package ru.kpfu.itis.genatulin.termwork.dto;

import ru.kpfu.itis.genatulin.termwork.validation.MatchingPasswords;
import ru.kpfu.itis.genatulin.termwork.validation.OneFilledField;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@MatchingPasswords
@OneFilledField
public class UpdateForm extends AbstractFormWithMatchingPasswords {
    @Email
    private String email;
    private String firstname;
    private String oldPassword;
    @Min(value = 3)
    @Max(value = 50)
    private String username;
}
