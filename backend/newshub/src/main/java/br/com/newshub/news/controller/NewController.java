package br.com.newshub.news.controller;


import br.com.newshub.client.response.NewsApiResponse;
import br.com.newshub.news.service.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewController {

    private final NewsService newsService;

    public NewController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public ResponseEntity<List<NewsApiResponse>> getAllNews() {
        return ResponseEntity.ok(newsService.getLatestNews());
    }
}
