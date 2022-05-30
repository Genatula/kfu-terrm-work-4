package ru.kpfu.itis.genatulin.termwork.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import ru.kpfu.itis.genatulin.termwork.dto.CommentForm;
import ru.kpfu.itis.genatulin.termwork.dto.CreateArticleForm;
import ru.kpfu.itis.genatulin.termwork.dto.UpdateArticleForm;
import ru.kpfu.itis.genatulin.termwork.dto.UpdateImageForm;
import ru.kpfu.itis.genatulin.termwork.exceptions.ArticleDoesNotExistException;
import ru.kpfu.itis.genatulin.termwork.exceptions.EmptyFileException;
import ru.kpfu.itis.genatulin.termwork.exceptions.IncorrectExtensionException;
import ru.kpfu.itis.genatulin.termwork.models.Article;
import ru.kpfu.itis.genatulin.termwork.models.User;
import ru.kpfu.itis.genatulin.termwork.services.ArticleService;
import ru.kpfu.itis.genatulin.termwork.services.StorageService;
import ru.kpfu.itis.genatulin.termwork.services.TagService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping(value = "/articles")
@Slf4j
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
    public String getAllArticles(ModelMap modelMap, @RequestParam(value = "page", defaultValue = "0") String page, HttpServletRequest request) {
        List<Article> articleList = articleService.getArticles();
        Boolean isAdmin = request.isUserInRole("ROLE_ADMIN");
        modelMap.addAttribute("articles", articleList);
        modelMap.addAttribute("is_admin", isAdmin);
        return "articles";
    }

    @GetMapping(value = "/{id}")
    public String getArticle(@PathVariable String id, ModelMap modelMap, Authentication authentication, HttpServletRequest request) {
        try {
            Boolean isAdmin = request.isUserInRole("ROLE_ADMIN");
            Article article = articleService.getArticle(Long.valueOf(id));
            User author = article.getAuthor();
            modelMap.addAttribute("article", article);
            modelMap.addAttribute("author", author);
            modelMap.addAttribute("form", new CommentForm());
            modelMap.addAttribute("comments", article.getComments());
            modelMap.addAttribute("is_admin", isAdmin);
            return "article";
        } catch (ArticleDoesNotExistException e) {
            log.info("Article with id " + id + "was not found");
            return "404";
        }
    }

    @PostMapping(value = "/{id}")
    public String addComment(@Valid @ModelAttribute("form") CommentForm form, BindingResult result, ModelMap modelMap, @PathVariable String id, HttpServletRequest request) {
        try {
            Boolean isAdmin = request.isUserInRole("ROLE_ADMIN");
            Article article = articleService.getArticle(Long.valueOf(id));
            User author = article.getAuthor();
            modelMap.addAttribute("article", article);
            modelMap.addAttribute("author", author);
            modelMap.addAttribute("comments", article.getComments());
            modelMap.addAttribute("is_admin", isAdmin);

            if (result.hasErrors()) {
                return "article";
            }

            articleService.addComment(form, Long.valueOf(id));
            return "redirect:/articles/" + id;
        } catch (ArticleDoesNotExistException e) {
            log.info("Article with id " + id + "was not found");
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
            log.info("Incorrect extension : " + form.getFile().getOriginalFilename());
            modelMap.addAttribute("incorrect_extension", true);
            return "article_create";
        } catch (EmptyFileException e) {
            log.info("Empty file has been uploaded");
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
            log.info("Article with id " + id + "was not found");
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
                log.info("Empty file");
                modelMap.addAttribute("empty_file", true);
                modelMap.addAttribute("tags", tagService.getTags());
                modelMap.addAttribute("checkedTags", article.getTags());
                return "article_edit";
            }
        } catch (ArticleDoesNotExistException e) {
            log.info("Article with id " + id + "was not found");
            return "404";
        }
    }

    @GetMapping(value = "/{id}/edit/image")
    public String getUpdateImageForm(@PathVariable String id, ModelMap modelMap) {
        try {
            Article article = articleService.getArticle(Long.valueOf(id));
            UpdateImageForm form = new UpdateImageForm();
            modelMap.addAttribute("article", article);
            modelMap.addAttribute("form", form);
            return "article_edit_image";
        } catch (ArticleDoesNotExistException e) {
            log.info("Article with id " + id + "was not found");
            return "404";
        }
    }

    @PostMapping(value = "/{id}/edit/image")
    public String updateImage(@Valid @ModelAttribute("form") UpdateImageForm form, BindingResult result, @PathVariable String id, ModelMap modelMap) {
        Article article = null;
        try {
            article = articleService.getArticle(Long.valueOf(id));
            if (result.hasErrors()) {
                modelMap.addAttribute("article", article);
                return "article_edit_image";
            }
            articleService.updateImage(form, Long.valueOf(id));
            return "redirect:/articles/" + id + "/edit";
        } catch (ArticleDoesNotExistException e) {
            log.info("Article with id " + id + "was not found");
            return "404";
        } catch (EmptyFileException e) {
            log.info("Empty file");
            modelMap.addAttribute("article", article);
            modelMap.addAttribute("empty_file", true);
            return "article_edit_image";
        }
    }
}
