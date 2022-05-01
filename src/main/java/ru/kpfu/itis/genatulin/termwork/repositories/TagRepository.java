package ru.kpfu.itis.genatulin.termwork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.genatulin.termwork.models.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
}