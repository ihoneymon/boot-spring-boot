package io.honeymon.boot.springboot.feature.v30;

import org.springframework.context.annotation.Configuration;

/**
 * Spring Boot 3.0 신기능: Jakarta EE 9 전환 및 주요 변경사항
 *
 * <h3>Jakarta EE 9 전환 (가장 중요한 변경)</h3>
 * <p>모든 {@code javax.*} 패키지가 {@code jakarta.*}로 변경됐다.</p>
 *
 * <h4>주요 패키지 변경</h4>
 * <ul>
 *   <li>{@code javax.servlet.*} → {@code jakarta.servlet.*}</li>
 *   <li>{@code javax.persistence.*} → {@code jakarta.persistence.*}</li>
 *   <li>{@code javax.validation.*} → {@code jakarta.validation.*}</li>
 *   <li>{@code javax.transaction.*} → {@code jakarta.transaction.*}</li>
 *   <li>{@code javax.annotation.*} → {@code jakarta.annotation.*}</li>
 * </ul>
 *
 * <h3>@HttpExchange — 선언적 HTTP 클라이언트</h3>
 * <p>{@code @HttpExchange}를 사용하면 인터페이스 기반으로 HTTP 클라이언트를 선언할 수 있다.
 * {@code WebClient} 또는 {@code RestClient}를 백엔드로 사용한다.</p>
 *
 * <pre>
 * {@literal @}HttpExchange("/api")
 * interface UserClient {
 *     {@literal @}GetExchange("/users/{id}")
 *     User getUser(@PathVariable Long id);
 *
 *     {@literal @}PostExchange("/users")
 *     User createUser(@RequestBody User user);
 * }
 *
 * // 등록 방법
 * {@literal @}Bean
 * UserClient userClient(WebClient.Builder builder) {
 *     WebClient client = builder.baseUrl("http://user-service").build();
 *     HttpServiceProxyFactory factory = HttpServiceProxyFactory
 *         .builderFor(WebClientAdapter.create(client)).build();
 *     return factory.createClient(UserClient.class);
 * }
 * </pre>
 *
 * <h3>Problem Details (RFC 7807)</h3>
 * <p>{@code spring.mvc.problemdetails.enabled=true} 설정으로 RFC 7807 형식의
 * 표준화된 에러 응답을 활성화할 수 있다.</p>
 *
 * <pre>
 * spring:
 *   mvc:
 *     problemdetails:
 *       enabled: true
 * </pre>
 *
 * <p>응답 예시:</p>
 * <pre>
 * {
 *   "type": "about:blank",
 *   "title": "Bad Request",
 *   "status": 400,
 *   "detail": "Invalid input",
 *   "instance": "/api/users"
 * }
 * </pre>
 *
 * <h3>Micrometer Observation API</h3>
 * <p>{@code ObservationRegistry}를 주입받아 메트릭과 트레이싱을 통합 수집할 수 있다.</p>
 *
 * <pre>
 * {@literal @}Component
 * class OrderService {
 *     private final ObservationRegistry registry;
 *
 *     void createOrder(Order order) {
 *         Observation.createNotStarted("order.create", registry)
 *             .lowCardinalityKeyValue("type", order.getType())
 *             .observe(() -> doCreate(order));
 *     }
 * }
 * </pre>
 *
 * <h3>Java 최소 버전</h3>
 * <p>{@code Spring Boot 3.0}부터 Java 17이 최소 요구 버전이다.</p>
 *
 * <h3>Deprecated 제거 (2.x → 3.0)</h3>
 * <ul>
 *   <li>{@code javax.*} 패키지 전체 — {@code jakarta.*}로 교체 필수</li>
 *   <li>{@code spring.factories} AutoConfiguration 등록 — {@code AutoConfiguration.imports}로 전환 필수</li>
 *   <li>레거시 {@code SpringApplicationBuilder} 일부 API 제거</li>
 * </ul>
 */
@Configuration
public class SpringBoot30JakartaConfig {
    // Jakarta EE 9 전환은 코드 전체에 영향을 미친다.
    // javax.* import를 jakarta.*로 일괄 변경해야 한다.
}
