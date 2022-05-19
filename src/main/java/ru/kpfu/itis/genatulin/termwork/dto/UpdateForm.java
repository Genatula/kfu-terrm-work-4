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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
