package io.honeymon.boot.springboot.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import lombok.Data;

@Service
public class RestClientExampleBean {

    private final RestClient restClient;

    public RestClientExampleBean() {
        this.restClient = RestClient.create();
    }

    /**
     * 간단한 예제를 만들어보가 찾다보니 나온 사이트.
     * <a href="https://jsonplaceholder.typicode.com/">JSONPlaceholder </a>
     * @param id
     * @return
     */
    public Post findOne(long id) {
        return this.restClient.get()
            .uri("https://jsonplaceholder.typicode.com/posts/{id}", id)
            .retrieve()
            .body(Post.class);
    }

    @Data
    public static class Post {
        private Long userId;
        private Long id;
        private String title;
        private String body;
    }
}
