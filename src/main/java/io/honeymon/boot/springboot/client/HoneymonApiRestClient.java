package io.honeymon.boot.springboot.client;

import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class HoneymonApiRestClient implements HoneymonApiClient {
    private final HoneymonApiProperties properties;
    private final RestTemplate restTemplate;
}
