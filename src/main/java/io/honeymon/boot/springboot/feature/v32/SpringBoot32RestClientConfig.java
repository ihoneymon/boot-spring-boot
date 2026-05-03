package io.honeymon.boot.springboot.feature.v32;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

/**
 * Spring Boot 3.2 신기능: RestClient, JdbcClient, Virtual Threads
 *
 * <h3>RestClient — 동기 HTTP 클라이언트 (신규)</h3>
 * <p>{@code RestClient}는 {@code RestTemplate}의 현대적 대안으로,
 * 유창한(fluent) API를 제공하는 동기 HTTP 클라이언트다.
 * {@code WebClient}처럼 사용하되 리액티브 의존성 없이 동기 방식으로 동작한다.</p>
 *
 * <pre>
 * {@literal @}Bean
 * RestClient restClient(RestClient.Builder builder) {
 *     return builder
 *         .baseUrl("http://api.example.com")
 *         .build();
 * }
 *
 * // 사용 예시
 * User user = restClient.get()
 *     .uri("/users/{id}", 1L)
 *     .retrieve()
 *     .body(User.class);
 *
 * restClient.post()
 *     .uri("/users")
 *     .contentType(MediaType.APPLICATION_JSON)
 *     .body(newUser)
 *     .retrieve()
 *     .toBodilessEntity();
 * </pre>
 *
 * <h3>JdbcClient — 유창한 JDBC 클라이언트 (신규)</h3>
 * <p>{@code JdbcClient}는 {@code JdbcTemplate}의 fluent 래퍼로,
 * 더 간결하고 직관적인 JDBC 조작을 지원한다.</p>
 *
 * <pre>
 * {@literal @}Repository
 * class UserRepository {
 *     private final JdbcClient jdbcClient;
 *
 *     List{@literal <User>} findAll() {
 *         return jdbcClient.sql("SELECT * FROM users")
 *             .query(User.class)
 *             .list();
 *     }
 *
 *     Optional{@literal <User>} findById(Long id) {
 *         return jdbcClient.sql("SELECT * FROM users WHERE id = :id")
 *             .param("id", id)
 *             .query(User.class)
 *             .optional();
 *     }
 * }
 * </pre>
 *
 * <h3>Virtual Threads (Project Loom)</h3>
 * <p>Java 21 가상 스레드를 활성화하려면 다음 설정을 추가한다:</p>
 *
 * <pre>
 * spring:
 *   threads:
 *     virtual:
 *       enabled: true
 * </pre>
 *
 * <p>활성화 시 Tomcat, Jetty의 요청 처리 스레드와 {@code @Async} 작업이
 * 가상 스레드로 실행된다. 블로킹 I/O가 많은 서비스에서 처리량이 향상된다.</p>
 *
 * <h3>Deprecated in 3.2</h3>
 * <ul>
 *   <li>{@code RestTemplate} 자동구성 — {@code RestClient} 사용 권장</li>
 *   <li>{@code JdbcTemplate} 직접 사용 — 단순 쿼리는 {@code JdbcClient} 사용 권장</li>
 * </ul>
 */
@Configuration
public class SpringBoot32RestClientConfig {

    /**
     * RestClient 커스터마이징 예시.
     * Spring Boot 4에서는 전용 RestClientCustomizer 대신 Builder에 필요한 설정을 직접 적용한다.
     */
    @Bean
    public RestClient loggingRestClient() {
        return RestClient.builder()
                .requestInterceptor((request, body, execution) -> {
                    // 요청 로깅 인터셉터 등록 예시
                    return execution.execute(request, body);
                })
                .build();
    }
}
