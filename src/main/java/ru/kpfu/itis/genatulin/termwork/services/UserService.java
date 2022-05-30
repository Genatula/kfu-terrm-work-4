package ru.kpfu.itis.genatulin.termwork.services;

import ru.kpfu.itis.genatulin.termwork.dto.SignUpForm;
import ru.kpfu.itis.genatulin.termwork.dto.UpdateForm;
import ru.kpfu.itis.genatulin.termwork.dto.UpdateImageForm;
import ru.kpfu.itis.genatulin.termwork.dto.UpdatePasswordForm;
import ru.kpfu.itis.genatulin.termwork.exceptions.*;
import ru.kpfu.itis.genatulin.termwork.models.User;

import java.io.IOException;

public interface UserService {
    void createUser(SignUpForm form) throws UserWithUsernameAlreadyExistsException, UserWithEmailAlreadyExistsException, IOException, FileDoesNotExistException;
    boolean checkIfExistsByUsername(String username);
    boolean checkIfExistsByEmail(String email);
    void updateUser(UpdateForm form, String username) throws UserWithUsernameAlreadyExistsException, UserWithEmailAlreadyExistsException;
    User getUserByUsername(String username) throws UserDoesNoxExistException;
    User getCurrentUser();
    void updatePassword(UpdatePasswordForm form) throws IncorrectPasswordException;
    void updateImage(UpdateImageForm form) throws EmptyFileException;
}
