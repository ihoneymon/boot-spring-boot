package io.honeymon.boot.springboot.feature.v34;

import org.springframework.context.annotation.Configuration;

/**
 * Spring Boot 3.4 신기능: Structured Logging 개선, MockMvcTester, 새 속성
 *
 * <h3>Structured Logging 개선</h3>
 * <p>3.4에서 Structured Logging에 커스텀 필드 추가 기능이 강화됐다.
 * {@code StructuredLogFormatter}를 구현하여 JSON 필드를 사용자 정의할 수 있다.</p>
 *
 * <pre>
 * logging:
 *   structured:
 *     format:
 *       console: ecs
 *     ecs:
 *       service:
 *         name: my-service
 *         version: 1.0.0
 *         environment: production
 *         node-name: node-1
 * </pre>
 *
 * <h3>MockMvcTester (AssertJ 통합)</h3>
 * <p>{@code MockMvcTester}는 AssertJ 스타일의 MVC 테스트를 지원한다.
 * 기존 {@code MockMvc} 대신 유창하고 타입 안전한 방식으로 테스트를 작성할 수 있다.</p>
 *
 * <pre>
 * {@literal @}WebMvcTest(UserController.class)
 * class UserControllerTest {
 *
 *     {@literal @}Autowired
 *     MockMvcTester mvc;
 *
 *     {@literal @}Test
 *     void getUser() {
 *         assertThat(mvc.get().uri("/users/1"))
 *             .hasStatusOk()
 *             .bodyJson()
 *             .extractingPath("$.name")
 *             .isEqualTo("홍길동");
 *     }
 * }
 * </pre>
 *
 * <h3>새로운 속성 및 변경사항</h3>
 * <ul>
 *   <li>{@code spring.application.name}: Banner에 자동 반영</li>
 *   <li>{@code spring.banner.location}: 배너 파일 위치 지정</li>
 *   <li>OTLP(OpenTelemetry Protocol) 트레이싱 개선 — OTLP 엔드포인트 직접 지원</li>
 * </ul>
 *
 * <pre>
 * management:
 *   otlp:
 *     tracing:
 *       endpoint: http://otel-collector:4318/v1/traces
 * </pre>
 *
 * <h3>@ConditionalOnMissingBean 개선</h3>
 * <p>제네릭 타입에 대한 {@code @ConditionalOnMissingBean} 처리가 개선됐다.
 * {@code Repository<User>}와 {@code Repository<Order>}를 별도 빈으로 정확하게 감지한다.</p>
 */
@Configuration
public class SpringBoot34StructuredLoggingConfig {
    // Structured Logging 커스텀 필드는 logging.structured.* 속성으로 제어한다.
}
