package ru.kpfu.itis.genatulin.termwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.genatulin.termwork.dto.SignUpForm;
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
}
