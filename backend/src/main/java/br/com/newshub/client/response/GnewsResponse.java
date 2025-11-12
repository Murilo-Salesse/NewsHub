package br.com.newshub.client.response;

import java.io.Serializable;
import java.util.List;

public record GnewsResponse(
        int totalArticles,
        List<NewsApiResponse> articles
) implements Serializable {
}
