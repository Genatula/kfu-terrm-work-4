package ru.kpfu.itis.genatulin.termwork.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.genatulin.termwork.validation.MatchingPasswords;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@MatchingPasswords
public class SignUpForm extends AbstractFormWithMatchingPasswords {
    @NotEmpty
    @NotNull
    private String firstname;
    @Email
    @NotNull
    @NotEmpty
    private String email;
    @Min(value = 3)
    @Max(value = 50)
    @NotEmpty
    @NotNull
    private String username;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
