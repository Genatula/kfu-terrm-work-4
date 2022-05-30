package ru.kpfu.itis.genatulin.termwork.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CreateMeetingForm {
    @Size(min = 10, max = 64, message = "The caption must be from 10 to 64 characters long")
    private String name;
    @NotNull
    @NotEmpty
    private String date;
    @NotNull
    @NotEmpty
    private String time;
    @NotNull
    @NotEmpty
    private String location;
    @Size(min = 100, message = "The body must consist of at least 100 characters")
    private String description;
    @Size(min = 20, max = 100, message = "The short description must be from 20 to 100 characters long")
    private String shortDescription;
    private MultipartFile file;
}
