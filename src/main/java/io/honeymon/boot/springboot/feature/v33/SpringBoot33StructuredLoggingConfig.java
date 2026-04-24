package io.honeymon.boot.springboot.feature.v33;

import org.springframework.context.annotation.Configuration;

/**
 * Spring Boot 3.3 신기능: Structured Logging(JSON) 및 CDS
 *
 * <h3>Structured Logging (JSON 로깅)</h3>
 * <p>{@code logging.structured.format.console} 설정으로 콘솔 로그를
 * JSON 형식으로 출력할 수 있다. ELK, Loki 등 로그 수집 시스템과 연동할 때 유용하다.</p>
 *
 * <pre>
 * logging:
 *   structured:
 *     format:
 *       console: ecs      # Elastic Common Schema 형식
 *       # 또는 logstash   # Logstash JSON 형식
 *       # 또는 gelf       # GELF(Graylog) 형식
 *   file:
 *     structured:
 *       format:
 *         file: ecs       # 파일 로그도 JSON으로 출력
 * </pre>
 *
 * <p>JSON 로그 출력 예시 (ECS 형식):</p>
 * <pre>
 * {
 *   "@timestamp": "2024-01-01T00:00:00.000Z",
 *   "log.level": "INFO",
 *   "message": "Started SpringBootApplication",
 *   "service.name": "boot-spring-boot",
 *   "process.pid": 12345,
 *   "log.logger": "o.s.boot.SpringApplication"
 * }
 * </pre>
 *
 * <h3>CDS (Class Data Sharing)</h3>
 * <p>CDS를 활용하면 애플리케이션 시작 시간과 메모리 사용량을 줄일 수 있다.
 * {spring-boot} 3.3부터 Gradle/Maven 플러그인으로 CDS 아카이브 생성을 지원한다.</p>
 *
 * <pre>
 * # CDS 아카이브 생성 (Gradle)
 * $ ./gradlew bootRun -Dspring-boot.run.jvmArguments="-XX:ArchiveClassesAtExit=app.jsa"
 *
 * # CDS 아카이브로 실행
 * $ java -XX:SharedArchiveFile=app.jsa -jar app.jar
 * </pre>
 *
 * <h3>@Fallback — 조건부 빈 등록</h3>
 * <p>{@code @Fallback}은 동일 타입의 다른 빈이 없을 때만 등록되는 폴백 빈을 정의한다.
 * {@code @ConditionalOnMissingBean}보다 의도가 명확하다.</p>
 *
 * <pre>
 * {@literal @}Fallback
 * {@literal @}Bean
 * CacheManager defaultCacheManager() {
 *     return new ConcurrentMapCacheManager();
 * }
 * </pre>
 *
 * <h3>ConnectionDetails 인터페이스</h3>
 * <p>{@code ConnectionDetails}를 통해 외부 서비스(DB, 메시지 브로커 등)의
 * 연결 정보를 추상화하고 런타임에 제공할 수 있다.</p>
 */
@Configuration
public class SpringBoot33StructuredLoggingConfig {
    // Structured Logging은 application.yml 설정만으로 활성화된다.
    // logging.structured.format.console=ecs|logstash|gelf
}
