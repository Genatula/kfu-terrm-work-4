package ru.kpfu.itis.genatulin.termwork.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CreateArticleForm {
    private String caption;
    private String shortDescription;
    private String body;
    private Set<String> tags;
}
