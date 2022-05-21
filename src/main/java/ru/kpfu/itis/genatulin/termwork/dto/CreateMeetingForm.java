package ru.kpfu.itis.genatulin.termwork.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CreateMeetingForm {
    private String name;
    private String date;
    private String time;
    private String location;
    private String description;
    private String shortDescription;
}
