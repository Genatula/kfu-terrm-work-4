package ru.kpfu.itis.genatulin.termwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.genatulin.termwork.dto.SignUpForm;
import ru.kpfu.itis.genatulin.termwork.dto.UpdateForm;
import ru.kpfu.itis.genatulin.termwork.dto.UpdatePasswordForm;
import ru.kpfu.itis.genatulin.termwork.exceptions.IncorrectPasswordException;
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
    private final StorageService storageService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, StorageService storageService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.storageService = storageService;
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
        authority.setAuthority("ROLE_ADMIN");
        User user = User.builder()
                .email(form.getEmail())
                .firstname(form.getFirstname())
                .password(passwordEncoder.encode(form.getPassword()))
                .enabled(true)
                .username(form.getUsername())
                .authorities(Set.of(authority))
                .build();
        authority.setUser(user);
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
        user.setEmail(form.getEmail());
        user.setUsername(form.getUsername());
        user.setFirstname(form.getFirstname());
        userRepository.save(user);
    }

    @Override
    public User getUserByUsername(String username) throws UserDoesNoxExistException {
        if (!checkIfExistsByUsername(username)) {
            throw new UserDoesNoxExistException();
        }
        return userRepository.getUserByUsername(username);
    }

    @Override
    public User getCurrentUser() {
        return userRepository.getCurrentUser();
    }

    @Override
    public void updatePassword(UpdatePasswordForm form) throws IncorrectPasswordException {
        if (!passwordEncoder.matches(form.getOldPassword(), userRepository.getCurrentUser().getPassword())) {
            throw new IncorrectPasswordException();
        }
        User user = userRepository.getCurrentUser();
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        userRepository.save(user);
    }
}
