package ru.kpfu.itis.genatulin.termwork.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SignUpForm {
    private String firstname;
    @Pattern(regexp = "^.*(?=.{8,})(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!#$%&? \"]).*$")
    private String password;
    private String matchingPassword;
    @Email
    private String email;
    @Min(value = 3)
    @Max(value = 50)
    private String username;
}
