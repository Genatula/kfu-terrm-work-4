package ru.kpfu.itis.genatulin.termwork.dto;

import javax.validation.constraints.Pattern;

public abstract class AbstractFormWithMatchingPasswords implements HavingMatchingPasswords {
    @Pattern(regexp = "^.*(?=.{8,})(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!#$%&? \"]).*$")
    private String password;
    private String matchingPassword;

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }
}
