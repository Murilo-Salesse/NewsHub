package br.com.newshub.client;

import br.com.newshub.client.response.GnewsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "GnewsClient", url = "${newshub.newsapi.base-url}")
public interface GnewsClient {

    @GetMapping("?q=Google&lang=pt&max=10&apikey=${newshub.newsapi.key}")
    GnewsResponse getLatestNews();

}
