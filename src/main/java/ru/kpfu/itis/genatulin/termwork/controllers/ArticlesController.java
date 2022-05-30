package ru.kpfu.itis.genatulin.termwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import ru.kpfu.itis.genatulin.termwork.dto.CommentForm;
import ru.kpfu.itis.genatulin.termwork.dto.CreateArticleForm;
import ru.kpfu.itis.genatulin.termwork.dto.UpdateArticleForm;
import ru.kpfu.itis.genatulin.termwork.exceptions.ArticleDoesNotExistException;
import ru.kpfu.itis.genatulin.termwork.exceptions.EmptyFileException;
import ru.kpfu.itis.genatulin.termwork.exceptions.FileDoesNotExistException;
import ru.kpfu.itis.genatulin.termwork.exceptions.IncorrectExtensionException;
import ru.kpfu.itis.genatulin.termwork.models.Article;
import ru.kpfu.itis.genatulin.termwork.models.User;
import ru.kpfu.itis.genatulin.termwork.services.ArticleService;
import ru.kpfu.itis.genatulin.termwork.services.StorageService;
import ru.kpfu.itis.genatulin.termwork.services.TagService;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping(value = "/articles")
public class ArticlesController {
    private final ArticleService articleService;
    private final TagService tagService;
    private final StorageService storageService;

    @Autowired
    public ArticlesController(ArticleService articleService, TagService tagService, StorageService storageService) {
        this.articleService = articleService;
        this.tagService = tagService;
        this.storageService = storageService;
    }

    @GetMapping
    public String getAllArticles(ModelMap modelMap, @RequestParam(value = "page", defaultValue = "0") String page) {
        List<Article> articleList = articleService.getArticles();
        modelMap.addAttribute("articles", articleList);
        return "articles";
    }

    @GetMapping(value = "/{id}")
    public String getArticle(@PathVariable String id, ModelMap modelMap) {
        try {
            Article article = articleService.getArticle(Long.valueOf(id));
            User author = article.getAuthor();
            modelMap.addAttribute("article", article);
            modelMap.addAttribute("author", author);
            modelMap.addAttribute("form", new CommentForm());
            modelMap.addAttribute("comments", article.getComments());
            return "article";
        } catch (ArticleDoesNotExistException e) {
            return "404";
        }
    }

    @PostMapping(value = "/{id}")
    public String addComment(@Valid @ModelAttribute("form") CommentForm form, BindingResult result, ModelMap modelMap, @PathVariable String id) {
        try {
            Article article = articleService.getArticle(Long.valueOf(id));
            User author = article.getAuthor();
            modelMap.addAttribute("article", article);
            modelMap.addAttribute("author", author);
            modelMap.addAttribute("comments", article.getComments());

            if (result.hasErrors()) {
                return "article";
            }

            articleService.addComment(form, Long.valueOf(id));
            return "redirect:/articles/" + id;
        } catch (ArticleDoesNotExistException e) {
            return "404";
        }
    }

    @GetMapping(value = "/create")
    public String getCreateForm(ModelMap modelMap) {
        modelMap.addAttribute("form", new CreateArticleForm());
        modelMap.addAttribute("tags", tagService.getTags());
        return "article_create";
    }

    @PostMapping(value = "/create")
    public String createArticle(@Valid @ModelAttribute("form") CreateArticleForm form, BindingResult result, Principal principal, RedirectAttributesModelMap redirectAttributesModelMap, ModelMap modelMap) {
        if (result.hasErrors()) {
            return "article_create";
        }
        try {
            articleService.createArticle(form, principal.getName());
        } catch (IncorrectExtensionException e) {
            modelMap.addAttribute("incorrect_extension", true);
            return "article_create";
        } catch (EmptyFileException e) {
            modelMap.addAttribute("empty_file", true);
            return "article_create";
        }
        redirectAttributesModelMap.addAttribute("created", true);
        return "redirect:/articles";
    }

    @GetMapping(value = "/{id}/edit")
    public String getArticleEditForm(@PathVariable(value = "id") String id, ModelMap modelMap) {
        try {
            Article article = articleService.getArticle(Long.valueOf(id));
            UpdateArticleForm form = new UpdateArticleForm();
            form.setBody(article.getBody());
            form.setCaption(article.getCaption());
            form.setShortDescription(article.getShortDescription());
            form.setId(id);

            modelMap.addAttribute("article", article);
            modelMap.addAttribute("form", form);
            modelMap.addAttribute("tags", tagService.getTags());
            modelMap.addAttribute("checkedTags", article.getTags());
            return "article_edit";
        } catch (ArticleDoesNotExistException e) {
            return "404";
        }
    }
    @PostMapping(value = "/{id}/edit")
    public String updateArticle(@Valid @ModelAttribute("form") UpdateArticleForm form, BindingResult result, @PathVariable("id") String id, RedirectAttributesModelMap redirectAttributesModelMap, ModelMap modelMap) {
        try {
            Article article = articleService.getArticle(Long.valueOf(id));
            if (result.hasErrors()) {
                modelMap.addAttribute("tags", tagService.getTags());
                modelMap.addAttribute("checkedTags", article.getTags());
                return "article_edit";
            }
            try {
                articleService.updateArticle(form, Long.valueOf(id));
                redirectAttributesModelMap.addAttribute("updated", true);
                return "redirect:/articles/" + id;
            } catch (EmptyFileException e) {
                modelMap.addAttribute("empty_file", true);
                modelMap.addAttribute("tags", tagService.getTags());
                modelMap.addAttribute("checkedTags", article.getTags());
                return "article_edit";
            }
        } catch (ArticleDoesNotExistException e) {
            return "404";
        }
    }
}
