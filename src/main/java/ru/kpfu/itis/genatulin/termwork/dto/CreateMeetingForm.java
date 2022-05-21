package ru.kpfu.itis.genatulin.termwork.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CreateMeetingForm {
    @Min(value = 10)
    @Max(value = 64)
    private String name;
    private String date;
    private String time;
    private String location;
    @Min(value = 100)
    private String description;
    @Min(value = 20)
    @Max(value = 100)
    private String shortDescription;
}
