package ru.kpfu.itis.genatulin.termwork.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.genatulin.termwork.models.AbstractFeedPost;
import ru.kpfu.itis.genatulin.termwork.services.ArticleService;
import ru.kpfu.itis.genatulin.termwork.services.MeetingService;
import ru.kpfu.itis.genatulin.termwork.services.SpeeddateService;
import ru.kpfu.itis.genatulin.termwork.utils.AbstractFeedPostComparator;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/feed")
public class FeedController {
    private final ArticleService articleService;
    private final MeetingService meetingService;
    private final SpeeddateService speeddateService;

    public FeedController(ArticleService articleService, MeetingService meetingService, SpeeddateService speeddateService) {
        this.articleService = articleService;
        this.meetingService = meetingService;
        this.speeddateService = speeddateService;
    }

    @GetMapping
    public String getFeed(ModelMap modelMap) {
        List<AbstractFeedPost> posts = new ArrayList<>();

        posts.addAll(articleService.getArticles());
        posts.addAll(meetingService.getMeetings());
        posts.addAll(speeddateService.getSpeeddates());
        posts.sort(new AbstractFeedPostComparator());
        modelMap.addAttribute("posts", posts);

        return "feed";
    }
}
