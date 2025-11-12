package br.com.newshub.savedNews.mapper;

import br.com.newshub.savedNews.dto.request.NewsRequest;
import br.com.newshub.savedNews.dto.response.NewsResponse;
import br.com.newshub.savedNews.model.SavedNew;
import br.com.newshub.user.model.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class NewsMapper {


    public static SavedNew toSavedNew(NewsRequest newsRequest) {
        return SavedNew.builder()
                .articleId(newsRequest.articleId())
                .title(newsRequest.articleId())
                .description(newsRequest.description())
                .url(newsRequest.url())
                .image(newsRequest.image())
                .publishedAt(newsRequest.publishedAt())
                .user(User.builder().id(newsRequest.userId()).build())
                .build();
    }

    public static NewsResponse toNewsReponse(SavedNew savedNew) {
        return NewsResponse
                .builder()
                .title(savedNew.getTitle())
                .description(savedNew.getDescription())
                .url(savedNew.getUrl())
                .image(savedNew.getImage())
                .publishedAt(savedNew.getPublishedAt())
                .build();
    }
}
