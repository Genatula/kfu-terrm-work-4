package ru.kpfu.itis.genatulin.termwork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.genatulin.termwork.models.FileDetails;

import java.util.Optional;

@Repository
public interface FileDetailsRepository extends JpaRepository<FileDetails, Long> {
    boolean existsByFilename(String filename);
    FileDetails getFileDetailsByFilename(String filename);
}