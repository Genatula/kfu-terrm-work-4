package ru.kpfu.itis.genatulin.termwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.genatulin.termwork.repositories.ArticleRepository;
import ru.kpfu.itis.genatulin.termwork.repositories.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ArticleService articleService;
    private final UserService userService;
    private final ArticleRepository articleRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, ArticleService articleService, UserService userService, ArticleRepository articleRepository) {
        this.commentRepository = commentRepository;
        this.articleService = articleService;
        this.userService = userService;
        this.articleRepository = articleRepository;
    }

}
