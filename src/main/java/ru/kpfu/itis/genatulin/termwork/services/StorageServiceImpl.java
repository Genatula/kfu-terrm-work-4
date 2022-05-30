package ru.kpfu.itis.genatulin.termwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.genatulin.termwork.exceptions.EmptyFileException;
import ru.kpfu.itis.genatulin.termwork.exceptions.FileDoesNotExistException;
import ru.kpfu.itis.genatulin.termwork.exceptions.IncorrectExtensionException;
import ru.kpfu.itis.genatulin.termwork.models.FileDetails;
import ru.kpfu.itis.genatulin.termwork.models.User;
import ru.kpfu.itis.genatulin.termwork.repositories.FileDetailsRepository;
import ru.kpfu.itis.genatulin.termwork.repositories.UserRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class StorageServiceImpl implements StorageService {
    private static final String[] imageExtension = new String[]{".jpg", ".png"};

    private final FileDetailsRepository fileDetailsRepository;
    private final UserRepository userRepository;

    @Value("${storage.images.path}")
    private String storagePath;

    @Autowired
    public StorageServiceImpl(FileDetailsRepository fileDetailsRepository, UserRepository userRepository) {
        this.fileDetailsRepository = fileDetailsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void updateImage(MultipartFile file, String filename) throws EmptyFileException {
        try {
            if (file.isEmpty()) {
                throw new EmptyFileException();
            }

            FileDetails fileDetails = getFileByName(filename);
            String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

            Files.delete(Paths.get(storagePath, filename + fileDetails.getExtension()));
            Files.copy(file.getInputStream(), Paths.get(storagePath, filename + extension));

            fileDetails.setContentType(file.getContentType());
            fileDetails.setExtension(extension);
            fileDetails.setSize(file.getSize());

            fileDetailsRepository.save(fileDetails);
        } catch (IOException | FileDoesNotExistException e) {
            throw new IllegalStateException();
        }
    }

    @Transactional
    @Override
    public void uploadUserImage(MultipartFile file) throws IncorrectExtensionException, EmptyFileException {
        User user = userRepository.getCurrentUser();
        uploadImage(file, user.getUsername());
    }

    @Transactional
    @Override
    public String uploadImage(MultipartFile file) throws IncorrectExtensionException, EmptyFileException {
        String filename = UUID.randomUUID().toString();
        uploadImage(file, filename);
        return filename;
    }

    @Transactional
    @Override
    public void uploadImage(MultipartFile file, String filename) throws IncorrectExtensionException, EmptyFileException {
        try {
            if (file == null || file.isEmpty()) {
                throw new EmptyFileException();
            }

            String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String newFilename = filename + extension;
            Files.copy(file.getInputStream(), Paths.get(storagePath, newFilename));

            FileDetails fileDetails = FileDetails.builder()
                    .filename(filename)
                    .contentType(file.getContentType())
                    .size(file.getSize())
                    .extension(extension)
                    .build();
            fileDetailsRepository.save(fileDetails);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public FileDetails getFileByName(String filename) throws FileDoesNotExistException {
        if (!checkIfExistsByFilename(filename)) {
            throw new FileDoesNotExistException();
        }
        return fileDetailsRepository.getFileDetailsByFilename(filename);
    }

    @Override
    public boolean checkIfExistsByFilename(String filename) {
        return fileDetailsRepository.existsByFilename(filename);
    }

    @Transactional
    @Override
    public FileDetails getDefaultUserImage() {
        FileDetails fileDetails = FileDetails.builder()
                .extension("jpg")
                .size(100L)
                .filename("default")
                .contentType("image/jpeg")
                .build();
        fileDetailsRepository.save(fileDetails);
        return fileDetails;
    }

    @Override
    public Path getPathByFilename(String filename) throws FileDoesNotExistException {
        FileDetails fileDetails = getFileByName(filename);
        return Paths.get(storagePath, filename + fileDetails.getExtension());
    }
}
