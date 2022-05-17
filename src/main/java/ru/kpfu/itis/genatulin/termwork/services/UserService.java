package ru.kpfu.itis.genatulin.termwork.services;

import ru.kpfu.itis.genatulin.termwork.dto.SignUpForm;
import ru.kpfu.itis.genatulin.termwork.exceptions.UserWithEmailAlreadyExistsException;
import ru.kpfu.itis.genatulin.termwork.exceptions.UserWithUsernameAlreadyExistsException;

public interface UserService {
    void createUser(SignUpForm form) throws UserWithUsernameAlreadyExistsException, UserWithEmailAlreadyExistsException;
    boolean checkIfExistsByUsername(String username);
    boolean checkIfExistsByEmail(String email);
}
