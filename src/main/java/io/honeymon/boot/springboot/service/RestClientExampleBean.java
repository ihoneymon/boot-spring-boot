package io.honeymon.boot.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.Data;

@Service
public class RestClientExampleBean {

    private final RestTemplate restTemplate;

    @Autowired
    public RestClientExampleBean(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    /**
     * 간단한 예제를 만들어보가 찾다보니 나온 사이트.
     * <a href="https://jsonplaceholder.typicode.com/">JSONPlaceholder </a>
     * @param id 
     * 
     * @return
     */
    public Post findOne(long id) {
        return this.restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts/{id}", Post.class, id);
    }
    
    @Data
    public static class Post {
        private Long userId;
        private Long id;
        private String title;
        private String body;
    }
}
