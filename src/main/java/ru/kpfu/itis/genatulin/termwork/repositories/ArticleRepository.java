package ru.kpfu.itis.genatulin.termwork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.genatulin.termwork.models.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}