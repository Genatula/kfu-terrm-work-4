package ru.kpfu.itis.genatulin.termwork.services;

import ru.kpfu.itis.genatulin.termwork.dto.SignUpForm;
import ru.kpfu.itis.genatulin.termwork.dto.UpdateForm;
import ru.kpfu.itis.genatulin.termwork.dto.UpdatePasswordForm;
import ru.kpfu.itis.genatulin.termwork.exceptions.IncorrectPasswordException;
import ru.kpfu.itis.genatulin.termwork.exceptions.UserDoesNoxExistException;
import ru.kpfu.itis.genatulin.termwork.exceptions.UserWithEmailAlreadyExistsException;
import ru.kpfu.itis.genatulin.termwork.exceptions.UserWithUsernameAlreadyExistsException;
import ru.kpfu.itis.genatulin.termwork.models.User;

public interface UserService {
    void createUser(SignUpForm form) throws UserWithUsernameAlreadyExistsException, UserWithEmailAlreadyExistsException;
    boolean checkIfExistsByUsername(String username);
    boolean checkIfExistsByEmail(String email);
    void updateUser(UpdateForm form, String username) throws UserWithUsernameAlreadyExistsException, UserWithEmailAlreadyExistsException;
    User getUserByUsername(String username) throws UserDoesNoxExistException;
    User getCurrentUser();
    void updatePassword(UpdatePasswordForm form) throws IncorrectPasswordException;
}
