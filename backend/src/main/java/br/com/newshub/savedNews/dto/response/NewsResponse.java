package br.com.newshub.savedNews.dto.response;

import lombok.Builder;

@Builder
public record NewsResponse(
        String title,
        String description,
        String url,
        String image,
        String publishedAt
) {
}
