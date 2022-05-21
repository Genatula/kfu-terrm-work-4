package ru.kpfu.itis.genatulin.termwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.genatulin.termwork.dto.CreateArticleForm;
import ru.kpfu.itis.genatulin.termwork.exceptions.ArticleDoesNotExistException;
import ru.kpfu.itis.genatulin.termwork.exceptions.UserDoesNoxExistException;
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

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository, UserService userService, TagService tagService) {
        this.articleRepository = articleRepository;
        this.userService = userService;
        this.tagService = tagService;
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
    public void createArticle(CreateArticleForm form, String username) {
        Set<Tag> tags = tagService.getTags(form.getTags());
        try {
            Article article = new Article();

            article.setAuthor(userService.getUserByUsername(username));
            article.setBody(form.getBody());
            article.setTags(tags);
            article.setCaption(form.getCaption());
            article.setCreationDate(new Date());
            article.setShortDescription(form.getShortDescription());

            articleRepository.save(article);
        } catch (UserDoesNoxExistException e) {
            throw new IllegalStateException();
        }
    }
}
