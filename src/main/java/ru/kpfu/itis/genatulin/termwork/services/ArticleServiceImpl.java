package ru.kpfu.itis.genatulin.termwork.services;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Override
    public boolean checkArticleId(Integer articleId, Authentication authentication) {
        if (authentication.isAuthenticated()) {
            return true;
        }
        return false;
    }
}
