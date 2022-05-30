package ru.kpfu.itis.genatulin.termwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.genatulin.termwork.models.Tag;
import ru.kpfu.itis.genatulin.termwork.repositories.TagRepository;

import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

@Service
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Set<Tag> getTags(Set<String> tags) {
        if (tags == null) {
            return new LinkedHashSet<>();
        }
        Set<Tag> tagSet = new LinkedHashSet<>();
        for (String tag : tags) {
            tagSet.add(tagRepository.getTagByName(tag));
        }
        return tagSet;
    }

    @Override
    public Set<Tag> getTags() {
        return new LinkedHashSet<>(tagRepository.findAll());
    }
}
