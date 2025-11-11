package br.com.newshub.savedNews.dto.request;

import lombok.Builder;

@Builder
public record NewsRequest(
        String articleId,
        String title,
        String description,
        String url,
        String image,
        String publishedAt,
        Long userId
) {
}
