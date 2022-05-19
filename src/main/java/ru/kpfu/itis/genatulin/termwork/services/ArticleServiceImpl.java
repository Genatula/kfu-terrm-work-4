package ru.kpfu.itis.genatulin.termwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.genatulin.termwork.models.Article;
import ru.kpfu.itis.genatulin.termwork.repositories.ArticleRepository;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
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
}
