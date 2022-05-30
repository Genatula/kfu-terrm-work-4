package ru.kpfu.itis.genatulin.termwork.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CreateArticleForm {
    private MultipartFile file;
    @Size(min = 10, max = 64, message = "Caption must be from 10 to 64 characters long")
    private String caption;
    @Size(min = 20, max = 100, message = "Short description must be from 20 to 100 characters long")
    private String shortDescription;
    @Size(min = 100, message = "The article must consist of at least 100 characters")
    private String body;
    private Set<String> tags;
}
