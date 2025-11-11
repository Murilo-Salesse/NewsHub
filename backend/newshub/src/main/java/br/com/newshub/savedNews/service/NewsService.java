package br.com.newshub.savedNews.service;

import br.com.newshub.client.GnewsClient;
import br.com.newshub.client.response.NewsApiResponse;
import br.com.newshub.savedNews.dto.request.NewsRequest;
import br.com.newshub.savedNews.dto.response.NewsResponse;
import br.com.newshub.savedNews.mapper.NewsMapper;
import br.com.newshub.savedNews.repository.NewRepository;
import br.com.newshub.response.ResponseModel;
import br.com.newshub.savedNews.model.SavedNew;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class NewsService {

    private final GnewsClient gnewsClient;
    private final NewRepository newRepository;

    public NewsService(GnewsClient gnewsClient, NewRepository newRepository) {
        this.gnewsClient = gnewsClient;
        this.newRepository = newRepository;
    }

    @Cacheable(value = "news", key = "'latest'")
    public List<NewsApiResponse> getLatestNews() {
        log.info("Fetching latest news...");
        var response = gnewsClient.getLatestNews();

        log.info("Fetched {} news items", response.articles().size());
        return response.articles();
    }

    public ResponseModel<String> saveFavorite(NewsRequest request) {
        SavedNew savedNew = NewsMapper.toSavedNew(request);
        newRepository.save(savedNew);

        return ResponseModel.ok("Salvo com sucesso");
    }

    public ResponseModel<List<NewsResponse>> getAllFavoriteNews(Long id) {
        List<SavedNew> savedNews = newRepository.findByUserId(id);

        List<NewsResponse> response = savedNews.stream()
                .map(NewsMapper::toNewsReponse)
                .toList();

        return new ResponseModel<>(response, "Lista de favoritos");
    }
}
