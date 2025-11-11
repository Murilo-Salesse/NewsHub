package br.com.newshub.news.service;

import br.com.newshub.client.GnewsClient;
import br.com.newshub.client.response.NewsApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class NewsService {

    private final GnewsClient gnewsClient;

    public NewsService(GnewsClient gnewsClient) {
        this.gnewsClient = gnewsClient;
    }

    public List<NewsApiResponse> getLatestNews() {
        log.info("Fetching latest news...");
        var response = gnewsClient.getLatestNews();
        log.info("Fetched {} news items", response.articles().size());
        return response.articles();
    }
}
