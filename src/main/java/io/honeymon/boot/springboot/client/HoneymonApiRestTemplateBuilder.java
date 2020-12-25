package io.honeymon.boot.springboot.client;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class HoneymonApiRestTemplateBuilder {
    public static RestTemplate build(HoneymonApiProperties properties) {
        return new RestTemplateBuilder()
                .rootUri(properties.getRootUri())
                .defaultHeader(HttpHeaders.AUTHORIZATION, properties.getHeaderAuthorization()) //Authorization: {AuthorizationKey}
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.getType()) //ContentType: application/json
//                .additionalInterceptors(new HoneymonApiHttpInterceptor(properties.getHeaderAuthorization()))
                .setReadTimeout(properties.getReadTimeout())
                .setConnectTimeout(properties.getConnectTimeout())
                .build();
    }
}
