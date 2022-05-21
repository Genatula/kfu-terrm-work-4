package ru.kpfu.itis.genatulin.termwork.services;

import org.springframework.security.core.Authentication;
import ru.kpfu.itis.genatulin.termwork.dto.CreateArticleForm;
import ru.kpfu.itis.genatulin.termwork.exceptions.ArticleDoesNotExistException;
import ru.kpfu.itis.genatulin.termwork.models.Article;

import java.util.List;

public interface ArticleService {
    boolean checkArticleId(Integer articleId, Authentication authentication);
    List<Article> getArticles();
    boolean checkIfExistsById(Long id);
    Article getArticle(Long id) throws ArticleDoesNotExistException;
    void createArticle(CreateArticleForm form, String username);
}
