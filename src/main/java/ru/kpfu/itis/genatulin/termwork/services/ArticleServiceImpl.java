package ru.kpfu.itis.genatulin.termwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.genatulin.termwork.dto.CreateArticleForm;
import ru.kpfu.itis.genatulin.termwork.dto.UpdateArticleForm;
import ru.kpfu.itis.genatulin.termwork.exceptions.*;
import ru.kpfu.itis.genatulin.termwork.models.Article;
import ru.kpfu.itis.genatulin.termwork.models.Tag;
import ru.kpfu.itis.genatulin.termwork.repositories.ArticleRepository;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final UserService userService;
    private final TagService tagService;
    private final StorageService storageService;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository, UserService userService, TagService tagService, StorageService storageService) {
        this.articleRepository = articleRepository;
        this.userService = userService;
        this.tagService = tagService;
        this.storageService = storageService;
    }

    @Override
    public boolean checkArticleId(Integer articleId, Authentication authentication) {
        return authentication.isAuthenticated();
    }

    @Override
    public List<Article> getArticles() {
        List<Article> result = articleRepository.findAll();
        if (result.size() < 20) {
            return result;
        }
        return result.subList(0, 20);
    }

    @Override
    public boolean checkIfExistsById(Long id) {
        return articleRepository.existsById(id);
    }

    @Override
    public Article getArticle(Long id) throws ArticleDoesNotExistException {
        if (!checkIfExistsById(id)) {
            throw new ArticleDoesNotExistException();
        }
        return articleRepository.getById(id);
    }

    @Override
    public void createArticle(CreateArticleForm form, String username) throws IncorrectExtensionException, EmptyFileException {
        Set<Tag> tags = tagService.getTags(form.getTags());
        try {
            Article article = new Article();

            String filename = storageService.uploadImage(form.getFile());

            article.setAuthor(userService.getUserByUsername(username));
            article.setBody(form.getBody());
            article.setTags(tags);
            article.setCaption(form.getCaption());
            article.setCreationDate(new Date());
            article.setShortDescription(form.getShortDescription());
            article.setImage(storageService.getFileByName(filename));

            articleRepository.save(article);
        } catch (UserDoesNoxExistException | FileDoesNotExistException e) {
            throw new IllegalStateException();
        }
    }

    @Override
    public void updateArticle(UpdateArticleForm form, Long id) {
        Article article = articleRepository.getById(id);
        Set<Tag> tags = tagService.getTags(form.getTags());

        article.setTags(tags);
        article.setCaption(form.getCaption());
        article.setBody(form.getBody());
        article.setShortDescription(form.getShortDescription());

        articleRepository.save(article);
    }
}
