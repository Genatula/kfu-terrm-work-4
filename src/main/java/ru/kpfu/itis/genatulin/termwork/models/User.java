package ru.kpfu.itis.genatulin.termwork.models;

import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Column(name = "firstname", nullable = false, length = 32)
    private String firstname;

    @Pattern(regexp = "^.*(?=.{8,})(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!#$%&? \"]).*$")
    @Column(name = "password", nullable = false, length = 500)
    private String password;

    @Email
    @Column(name = "email", nullable = false, unique = true, length = 64)
    private String email;

    @Id
    @Min(value = 3)
    @Max(value = 50)
    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled = false;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return username != null && Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}