package ru.kpfu.itis.genatulin.termwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.genatulin.termwork.dto.SignUpForm;
import ru.kpfu.itis.genatulin.termwork.dto.UpdateForm;
import ru.kpfu.itis.genatulin.termwork.exceptions.UserDoesNoxExistException;
import ru.kpfu.itis.genatulin.termwork.exceptions.UserWithEmailAlreadyExistsException;
import ru.kpfu.itis.genatulin.termwork.exceptions.UserWithUsernameAlreadyExistsException;
import ru.kpfu.itis.genatulin.termwork.models.Authority;
import ru.kpfu.itis.genatulin.termwork.models.User;
import ru.kpfu.itis.genatulin.termwork.repositories.UserRepository;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createUser(SignUpForm form) throws UserWithUsernameAlreadyExistsException, UserWithEmailAlreadyExistsException {
        if (checkIfExistsByUsername(form.getUsername())) {
            throw new UserWithUsernameAlreadyExistsException();
        }
        else if (checkIfExistsByEmail(form.getEmail())) {
            throw new UserWithEmailAlreadyExistsException();
        }
        Authority authority = new Authority();
        authority.setAuthority("USER");
        User user = User.builder()
                .email(form.getEmail())
                .firstname(form.getFirstname())
                .password(passwordEncoder.encode(form.getPassword()))
                .enabled(true)
                .username(form.getUsername())
                .authorities(Set.of(authority))
                .build();
        userRepository.save(user);
    }

    @Override
    public boolean checkIfExistsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean checkIfExistsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void updateUser(UpdateForm form, String username) throws UserWithUsernameAlreadyExistsException, UserWithEmailAlreadyExistsException {
        if (checkIfExistsByUsername(form.getUsername())) {
            throw new UserWithUsernameAlreadyExistsException();
        }
        else if (checkIfExistsByEmail(form.getEmail())) {
            throw new UserWithEmailAlreadyExistsException();
        }
        User user = userRepository.getUserByUsername(username);
        User updatedUser = User.builder()
                .id(user.getId())
                .email(form.getEmail().equals("") || form.getEmail() == null ? user.getEmail() : form.getEmail())
                .username(form.getUsername().equals("") || form.getUsername() == null ? user.getUsername() : form.getUsername())
                .firstname(form.getFirstname().equals("") || form.getFirstname() == null ? user.getFirstname() : form.getFirstname())
                .password(form.getPassword().equals("") || form.getPassword() == null ? user.getPassword() : passwordEncoder.encode(form.getPassword()))
                .authorities(user.getAuthorities())
                .enabled(user.getEnabled())
                .build();
        userRepository.save(updatedUser);
    }

    @Override
    public User getUserByUsername(String username) throws UserDoesNoxExistException {
        if (!checkIfExistsByUsername(username)) {
            throw new UserDoesNoxExistException();
        }
        return userRepository.getUserByUsername(username);
    }
}
