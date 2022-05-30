package ru.kpfu.itis.genatulin.termwork.services;

import ru.kpfu.itis.genatulin.termwork.models.Tag;

import java.util.Set;

public interface TagService {
    Set<Tag> getTags(Set<String> tags);
    Set<Tag> getTags();
}
