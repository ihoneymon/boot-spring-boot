package io.honeymon.boot.springboot.feature.v40;

import org.springframework.context.annotation.Configuration;

/**
 * Spring Boot 4.0 신기능: Spring Framework 7, 모듈 재편
 *
 * <h3>Spring Framework 7 기반</h3>
 * <p>{@code Spring Boot 4.0}은 {@code Spring Framework 7}을 기반으로 한다.
 * Java 17이 최소 요구 버전이며, Java 21 이상을 권장한다.</p>
 *
 * <h3>@Fallback 및 @Bean(bootstrap) 개선</h3>
 * <p>{@code @Fallback}과 {@code @Bean(bootstrap = Bootstrap.BACKGROUND)}가
 * 정식 지원으로 안정화됐다.</p>
 *
 * <h3>HTTP 인터페이스 개선 (@HttpExchange)</h3>
 * <p>{@code @HttpExchange} 기반 HTTP 인터페이스에 에러 처리와 재시도 설정이
 * 더 간편해졌다. {@code RestClient.Builder}와의 통합이 강화됐다.</p>
 *
 * <pre>
 * {@literal @}Bean
 * UserClient userClient(RestClient.Builder builder) {
 *     RestClient client = builder.baseUrl("http://user-service").build();
 *     HttpServiceProxyFactory factory = HttpServiceProxyFactory
 *         .builderFor(RestClientAdapter.create(client)).build();
 *     return factory.createClient(UserClient.class);
 * }
 * </pre>
 *
 * <h3>Deprecated 제거 (3.x → 4.0)</h3>
 * <ul>
 *   <li>{@code RestTemplateBuilder} 자동구성 제거 — {@code RestClient.Builder} 사용 필수</li>
 *   <li>레거시 {@code spring-boot-starter-web} 내 {@code RestTemplate} 자동구성 제거</li>
 *   <li>{@code @AutoConfigureBefore} / {@code @AutoConfigureAfter} 제거
 *       — {@code @AutoConfiguration(before/after)} 사용 필수</li>
 *   <li>일부 {@code spring-boot-actuator} 엔드포인트 구조 변경</li>
 * </ul>
 *
 * <h3>모듈 재편</h3>
 * <p>일부 스타터 의존성 구조가 변경됐다. 기존에 전이 의존성으로 포함되던 라이브러리가
 * 분리되어 명시적으로 추가해야 하는 경우가 있다.</p>
 *
 * <h3>Observability 개선</h3>
 * <p>Micrometer Observation API와 OpenTelemetry 통합이 더욱 강화됐다.
 * OTLP Exporter를 통한 메트릭/트레이싱 내보내기가 기본 지원된다.</p>
 *
 * <pre>
 * management:
 *   otlp:
 *     metrics:
 *       export:
 *         url: http://otel-collector:4318/v1/metrics
 *     tracing:
 *       endpoint: http://otel-collector:4318/v1/traces
 * </pre>
 *
 * <h3>Security 기본값 변경</h3>
 * <p>Spring Security 7과 함께 일부 기본 보안 설정이 변경됐다.
 * CSRF 설정, CORS 기본값 등을 명시적으로 구성하는 것을 권장한다.</p>
 */
@Configuration
public class SpringBoot40Framework7Config {
    // Spring Boot 4.0은 Spring Framework 7 기반의 대규모 메이저 업그레이드다.
    // 마이그레이션 전 반드시 각 의존성 라이브러리의 4.0 호환성을 확인해야 한다.
}
