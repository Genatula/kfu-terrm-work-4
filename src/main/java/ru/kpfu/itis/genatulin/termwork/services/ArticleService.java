package ru.kpfu.itis.genatulin.termwork.services;

import org.springframework.security.core.Authentication;
import ru.kpfu.itis.genatulin.termwork.dto.CommentForm;
import ru.kpfu.itis.genatulin.termwork.dto.CreateArticleForm;
import ru.kpfu.itis.genatulin.termwork.dto.UpdateArticleForm;
import ru.kpfu.itis.genatulin.termwork.exceptions.ArticleDoesNotExistException;
import ru.kpfu.itis.genatulin.termwork.exceptions.EmptyFileException;
import ru.kpfu.itis.genatulin.termwork.exceptions.IncorrectExtensionException;
import ru.kpfu.itis.genatulin.termwork.models.Article;

import java.util.List;

public interface ArticleService {
    boolean checkArticleId(Integer articleId, Authentication authentication);
    List<Article> getArticles();
    boolean checkIfExistsById(Long id);
    Article getArticle(Long id) throws ArticleDoesNotExistException;
    void createArticle(CreateArticleForm form, String username) throws IncorrectExtensionException, EmptyFileException;
    void updateArticle(UpdateArticleForm form, Long id) throws EmptyFileException;
    void addComment(CommentForm form, Long id) throws ArticleDoesNotExistException;
}
