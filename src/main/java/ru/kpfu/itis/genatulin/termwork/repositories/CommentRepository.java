package ru.kpfu.itis.genatulin.termwork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.genatulin.termwork.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}