package ru.kpfu.itis.genatulin.termwork.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CreateArticleForm {
    @Min(value = 10)
    @Max(value = 64)
    @NotNull
    @NotEmpty
    private String caption;
    @Min(value = 20)
    @Max(value = 100)
    @NotNull
    @NotEmpty
    private String shortDescription;
    @Min(value = 100)
    @NotNull
    @NotEmpty
    private String body;
    private Set<String> tags;
}