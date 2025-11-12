package br.com.newshub.savedNews.controller;


import br.com.newshub.client.response.NewsApiResponse;
import br.com.newshub.savedNews.dto.request.NewsRequest;
import br.com.newshub.savedNews.dto.response.NewsResponse;
import br.com.newshub.savedNews.service.NewsService;
import br.com.newshub.response.ResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel<List<NewsResponse>>> getAllFavorites(@PathVariable("id") Long id) {

        return ResponseEntity.ok(newsService.getAllFavoriteNews(id));
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseModel<String>> saveNews(@RequestBody NewsRequest request) {
        System.out.println("salvo: " + request);

        return ResponseEntity.ok(newsService.saveFavorite(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseModel<String>> deleteNews(@PathVariable("id") Long id) {
        return ResponseEntity.ok(newsService.deleteFavoriteNews(id));
    }

    @DeleteMapping
    public ResponseEntity<ResponseModel<String>> deleteAllNews() {
        return ResponseEntity.ok(newsService.deleteAllFavoriteNews());
    }
}
