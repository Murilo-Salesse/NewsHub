package br.com.newshub.client.response;

import java.io.Serializable;

public record NewsApiResponse(
        String id,
        String title,
        String description,
        String content,
        String url,
        String image,
        String publishedAt
) implements Serializable {
}
