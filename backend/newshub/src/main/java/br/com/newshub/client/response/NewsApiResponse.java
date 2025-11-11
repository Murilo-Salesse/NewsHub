package br.com.newshub.client.response;

public record NewsApiResponse(
        String title,
        String description,
        String content,
        String url,
        String image,
        String publishedAt
) {
}
