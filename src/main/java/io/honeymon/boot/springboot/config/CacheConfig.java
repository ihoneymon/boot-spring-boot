package io.honeymon.boot.springboot.config;

import java.time.Duration;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;

@Configuration
public class CacheConfig {

	@Bean
	public CacheManagerCustomizer<RedisCacheManager> redisCacheManager() {
		return new CacheManagerCustomizer<RedisCacheManager>() {

			@Override
			public void customize(RedisCacheManager cacheManager) {
				cacheManager.getCacheNames().forEach(c -> {
					cacheManager.getCacheConfigurations().get(c).entryTtl(Duration.ofMillis(60_000));
				});
			}
		};
	}
}
