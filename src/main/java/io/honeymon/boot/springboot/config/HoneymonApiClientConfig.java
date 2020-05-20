package io.honeymon.boot.springboot.config;

import io.honeymon.boot.springboot.client.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class HoneymonApiClientConfig {
    @Configuration
    @ConditionalOnProperty(value = "honeymon.api.client-mode", havingValue = "rest")
    @EnableConfigurationProperties({HoneymonApiProperties.class})
    public static class HoneymonApiRestClientConfig {
        @Bean
        public HoneymonApiClient honeymonApiClient(HoneymonApiProperties properties) {
            return new HoneymonApiRestClient(properties, HoneymonApiRestTemplateBuilder.build(properties));
        }
    }

    @Configuration
    @ConditionalOnProperty(value = "honeymon.api.client-mode", havingValue = "stub")
    public static class HoneymonApiStubClientConfig {
        @Bean
        public HoneymonApiClient honeymonApiClient() {
            return new HoneymonApiStubClient();
        }
    }
}
