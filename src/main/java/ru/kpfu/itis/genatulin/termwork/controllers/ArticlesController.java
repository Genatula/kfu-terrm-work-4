package ru.kpfu.itis.genatulin.termwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.genatulin.termwork.models.Article;
import ru.kpfu.itis.genatulin.termwork.services.ArticleService;

import java.util.List;


@Controller
@RequestMapping(value = "/articles")
public class ArticlesController {
    private final ArticleService articleService;

    @Autowired
    public ArticlesController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public String getAllArticles(ModelMap modelMap, @RequestParam(value = "page", defaultValue = "0") String page) {
        List<Article> articleList = articleService.getArticles();
        modelMap.addAttribute("articles", articleList);
        return "articles";
    }

    @GetMapping(value = "/{id}")
    public String getArticle(ModelMap modelMap, @PathVariable(value = "id") String id) {
        return "article";
    }
}
