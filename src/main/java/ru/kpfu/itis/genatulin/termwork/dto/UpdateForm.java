package ru.kpfu.itis.genatulin.termwork.dto;

import javax.validation.constraints.*;

public class UpdateForm {
    @Email(message = "Invalid email format")
    private String email;
    @NotEmpty
    @NotNull
    private String firstname;

    @Size(min = 3, max = 64, message = "Username must be from 3 to 64 characters long")
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


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
