package ru.kpfu.itis.genatulin.termwork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.genatulin.termwork.models.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}