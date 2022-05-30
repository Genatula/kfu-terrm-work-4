package ru.kpfu.itis.genatulin.termwork.dto;

import org.springframework.web.multipart.MultipartFile;

public class UpdateImageForm {
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
