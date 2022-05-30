package ru.kpfu.itis.genatulin.termwork.dto;

import ru.kpfu.itis.genatulin.termwork.validation.MatchingPasswords;

@MatchingPasswords
public class UpdatePasswordForm extends AbstractFormWithMatchingPasswords{
    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    private String oldPassword;
}
