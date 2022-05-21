package ru.kpfu.itis.genatulin.termwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import ru.kpfu.itis.genatulin.termwork.dto.CreateArticleForm;
import ru.kpfu.itis.genatulin.termwork.exceptions.ArticleDoesNotExistException;
import ru.kpfu.itis.genatulin.termwork.models.Article;
import ru.kpfu.itis.genatulin.termwork.services.ArticleService;

import java.security.Principal;
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

    @GetMapping(value = {"/{id}", "/{id}/edit"})
    public String getArticle(ModelMap modelMap, @PathVariable(value = "id") String id) {
        try {
            Article article = articleService.getArticle(Long.valueOf(id));
            modelMap.addAttribute("article", article);
            return "article";
        } catch (ArticleDoesNotExistException e) {
            return "404";
        }
    }

    @GetMapping(value = "/create")
    public String getCreateForm() {
        return "article_create";
    }

    @PostMapping(value = "/create")
    public String createArticle(CreateArticleForm form, Principal principal, RedirectAttributesModelMap redirectAttributesModelMap) {
        articleService.createArticle(form, principal.getName());
        redirectAttributesModelMap.addAttribute("created", true);
        return "redirect:/articles";
    }
}
