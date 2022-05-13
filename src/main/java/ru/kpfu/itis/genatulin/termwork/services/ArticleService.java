package ru.kpfu.itis.genatulin.termwork.services;

import org.springframework.security.core.Authentication;

public interface ArticleService {
    boolean checkArticleId(Integer articleId, Authentication authentication);
}
