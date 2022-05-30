package ru.kpfu.itis.genatulin.termwork.services;

import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.genatulin.termwork.exceptions.EmptyFileException;
import ru.kpfu.itis.genatulin.termwork.exceptions.FileDoesNotExistException;
import ru.kpfu.itis.genatulin.termwork.exceptions.IncorrectExtensionException;
import ru.kpfu.itis.genatulin.termwork.models.FileDetails;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

public interface StorageService {
    void updateImage(MultipartFile file, String filename) throws EmptyFileException;
    void uploadUserImage(MultipartFile file) throws IncorrectExtensionException, EmptyFileException;
    String uploadImage(MultipartFile file) throws IncorrectExtensionException, EmptyFileException;
    void uploadImage(MultipartFile file, String filename) throws IncorrectExtensionException, EmptyFileException;
    FileDetails getFileByName(String filename) throws FileDoesNotExistException;
    boolean checkIfExistsByFilename(String filename);
    String uploadDefaultUserImage() throws IOException;
    Path getPathByFilename(String filename) throws FileDoesNotExistException;
}
