package io.honeymon.boot.springboot.feature.v26;

import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Boot 2.6 신기능: Kubernetes Probes (liveness/readiness)
 *
 * <p>Spring Boot 2.6에서 쿠버네티스 Liveness/Readiness 프로브가 정식 지원됐다.
 * {@code /actuator/health/liveness}와 {@code /actuator/health/readiness} 엔드포인트가
 * 쿠버네티스 배포 환경에서 파드 상태를 정확하게 반영한다.</p>
 *
 * <h3>application.yml 설정</h3>
 * <pre>
 * management:
 *   endpoint:
 *     health:
 *       probes:
 *         enabled: true          # 프로브 엔드포인트 활성화
 *   health:
 *     livenessstate:
 *       enabled: true
 *     readinessstate:
 *       enabled: true
 * </pre>
 *
 * <p>쿠버네티스 환경이 아닌 경우에도 아래와 같이 명시적으로 활성화할 수 있다.</p>
 *
 * <h3>PathPatternParser 기본 전환 (2.6+)</h3>
 * <p>{@code PathPatternParser}가 기본 URL 매칭 전략으로 변경됐다.
 * 기존 {@code AntPathMatcher}에서 전환되며, 아래 속성이 <strong>deprecated</strong>됐다:</p>
 * <ul>
 *   <li>{@code spring.mvc.pathmatch.use-suffix-pattern=true} → 더 이상 지원 안 함</li>
 *   <li>{@code spring.mvc.pathmatch.use-registered-suffix-pattern=true} → 더 이상 지원 안 함</li>
 * </ul>
 *
 * <h3>순환 의존성 금지 (2.6+ 기본값)</h3>
 * <p>순환 의존성(circular dependency)이 기본으로 금지됐다.
 * 필요한 경우 {@code spring.main.allow-circular-references=true}로 우회할 수 있으나,
 * 순환 의존성 자체를 제거하는 것을 권장한다.</p>
 *
 * <h3>Deprecated in 2.6</h3>
 * <ul>
 *   <li>{@code spring.mvc.pathmatch.use-suffix-pattern} 속성 제거</li>
 *   <li>Actuator {@code /actuator/health} 응답 구조 변경
 *       — 하위 컴포넌트가 {@code components} 아래로 이동</li>
 * </ul>
 */
@Configuration
public class SpringBoot26HealthProbeConfig {

    /**
     * 커스텀 Liveness 상태를 나타내는 HealthIndicator 예시.
     * 실제 쿠버네티스 환경에서는 Spring이 자동으로 LivenessState를 관리한다.
     */
    @Bean
    public HealthIndicator customAppHealthIndicator() {
        return () -> Health.up()
                .withDetail("feature", "spring-boot-2.6")
                .withDetail("probe", "custom-health-indicator")
                .build();
    }
}
