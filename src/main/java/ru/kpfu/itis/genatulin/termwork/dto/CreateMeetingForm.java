package ru.kpfu.itis.genatulin.termwork.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CreateMeetingForm {
    @Min(value = 10)
    @Max(value = 64)
    @NotNull
    @NotEmpty
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
    @Min(value = 100)
    @NotNull
    @NotEmpty
    private String description;
    @Min(value = 20)
    @Max(value = 100)
    @NotNull
    @NotEmpty
    private String shortDescription;
}
