package ru.kpfu.itis.genatulin.termwork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.genatulin.termwork.models.Tag;

import javax.validation.constraints.Min;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    boolean existsByName(String name);
    Tag getTagByName(String name);
}