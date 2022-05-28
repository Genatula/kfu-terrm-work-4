package ru.kpfu.itis.genatulin.termwork.services;

import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.genatulin.termwork.exceptions.IncorrectExtensionException;

public interface StorageService {
    void uploadArticleImage(MultipartFile file, Integer id) throws IncorrectExtensionException;
    void uploadMeetingImage(MultipartFile file, Integer id) throws IncorrectExtensionException;
    void uploadSpeeddateImage(MultipartFile file, Integer id) throws IncorrectExtensionException;
    void uploadUserImage(MultipartFile file) throws IncorrectExtensionException;
    void uploadImage(MultipartFile file) throws IncorrectExtensionException;
    void uploadImage(MultipartFile file, String filename) throws IncorrectExtensionException;
}
