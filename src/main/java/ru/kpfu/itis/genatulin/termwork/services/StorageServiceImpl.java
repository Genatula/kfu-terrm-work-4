package ru.kpfu.itis.genatulin.termwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.genatulin.termwork.exceptions.IncorrectExtensionException;
import ru.kpfu.itis.genatulin.termwork.models.FileDetails;
import ru.kpfu.itis.genatulin.termwork.models.User;
import ru.kpfu.itis.genatulin.termwork.repositories.FileDetailsRepository;
import ru.kpfu.itis.genatulin.termwork.repositories.UserRepository;

import java.io.IOException;
import java.nio.file.Files;
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

    @Transactional
    @Override
    public void uploadArticleImage(MultipartFile file, Integer id) {

    }

    @Transactional
    @Override
    public void uploadMeetingImage(MultipartFile file, Integer id) {

    }

    @Transactional
    @Override
    public void uploadSpeeddateImage(MultipartFile file, Integer id) {

    }

    @Transactional
    @Override
    public void uploadUserImage(MultipartFile file) throws IncorrectExtensionException {
        User user = userRepository.getCurrentUser();
        uploadImage(file, user.getUsername());
    }

    @Transactional
    @Override
    public void uploadImage(MultipartFile file) throws IncorrectExtensionException {
        uploadImage(file, UUID.randomUUID().toString());
    }

    @Transactional
    @Override
    public void uploadImage(MultipartFile file, String filename) throws IncorrectExtensionException {
        try {
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
}
