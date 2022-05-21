package ru.kpfu.itis.genatulin.termwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import ru.kpfu.itis.genatulin.termwork.dto.CreateArticleForm;
import ru.kpfu.itis.genatulin.termwork.dto.UpdateArticleForm;
import ru.kpfu.itis.genatulin.termwork.exceptions.ArticleDoesNotExistException;
import ru.kpfu.itis.genatulin.termwork.models.Article;
import ru.kpfu.itis.genatulin.termwork.services.ArticleService;

import javax.validation.Valid;
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

    @GetMapping(value = "/{id}")
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
    public String createArticle(@Valid CreateArticleForm form, Principal principal, RedirectAttributesModelMap redirectAttributesModelMap, BindingResult result) {
        if (result.hasErrors()) {
            return "article_create";
        }
        articleService.createArticle(form, principal.getName());
        redirectAttributesModelMap.addAttribute("created", true);
        return "redirect:/articles";
    }

    @GetMapping(value = "/{id}/edit")
    public String getArticleEditForm(ModelMap modelMap, @PathVariable(value = "id") String id) {
        try {
            Article article = articleService.getArticle(Long.valueOf(id));
            modelMap.addAttribute("article", article);
            return "article_edit";
        } catch (ArticleDoesNotExistException e) {
            return "404";
        }
    }
    @PostMapping(value = "/{id}/edit")
    public String updateArticle(@Valid UpdateArticleForm form, @PathVariable(value = "id") String id, BindingResult result, RedirectAttributesModelMap redirectAttributesModelMap) {
        if (result.hasErrors()) {
            return "article_edit";
        }
        articleService.updateArticle(form, Long.valueOf(id));
        redirectAttributesModelMap.addAttribute("updated", true);
        return "redirect:/articles/" + id;
    }
}
