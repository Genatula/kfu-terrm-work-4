package ru.kpfu.itis.genatulin.termwork.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.genatulin.termwork.validation.MatchingPasswords;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@MatchingPasswords(message = "Passwords must match")
public class SignUpForm extends AbstractFormWithMatchingPasswords {
    @NotEmpty(message = "Firstname must not be empty")
    @NotNull(message = "Firstname must not be empty")
    private String firstname;
    @Email(message = "Invalid email format")
    @NotNull(message = "Email must not be empty")
    @NotEmpty(message = "Email must not be empty")
    private String email;
    @Size(min = 3, max = 50)
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
