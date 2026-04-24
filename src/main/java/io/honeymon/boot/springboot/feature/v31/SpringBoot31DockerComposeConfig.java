package io.honeymon.boot.springboot.feature.v31;

import org.springframework.context.annotation.Configuration;

/**
 * Spring Boot 3.1 신기능: Docker Compose 지원 및 SSL Bundle
 *
 * <h3>Docker Compose 지원</h3>
 * <p>{@code spring-boot-docker-compose} 의존성을 추가하면 개발 시
 * {@code docker-compose.yml}을 자동으로 감지하고 애플리케이션 시작 시
 * {@code docker compose up}을 실행한다.</p>
 *
 * <pre>
 * // build.gradle
 * dependencies {
 *     developmentOnly 'org.springframework.boot:spring-boot-docker-compose'
 * }
 * </pre>
 *
 * <p>Docker Compose 연동 동작 제어:</p>
 * <pre>
 * spring:
 *   docker:
 *     compose:
 *       enabled: true                    # 기본값 true
 *       lifecycle-management: start-and-stop  # 시작/종료 시 함께 관리
 *       file: custom-compose.yml         # 파일 위치 지정
 * </pre>
 *
 * <h3>SSL Bundle</h3>
 * <p>SSL 인증서와 키를 중앙에서 관리하고 여러 컴포넌트(서버, DB, REST 클라이언트)에서
 * 재사용할 수 있는 SSL Bundle이 추가됐다.</p>
 *
 * <pre>
 * spring:
 *   ssl:
 *     bundle:
 *       jks:
 *         my-ssl:
 *           keystore:
 *             location: classpath:keystore.jks
 *             password: changeit
 *             type: JKS
 *           truststore:
 *             location: classpath:truststore.jks
 *             password: changeit
 * </pre>
 *
 * <p>SSL Bundle 사용:</p>
 * <pre>
 * {@literal @}Bean
 * RestClient restClient(RestClient.Builder builder, SslBundles sslBundles) {
 *     return builder
 *         .apply(sslBundles.getBundle("my-ssl"))
 *         .build();
 * }
 * </pre>
 *
 * <h3>Service Connection (@ServiceConnection)</h3>
 * <p>테스트에서 {@code @ServiceConnection}을 사용하면 Testcontainers와의 연결을 자동구성할 수 있다:</p>
 *
 * <pre>
 * {@literal @}SpringBootTest
 * class MyTest {
 *
 *     {@literal @}ServiceConnection
 *     {@literal @}Container
 *     static PostgreSQLContainer{@literal <?>} postgres = new PostgreSQLContainer<>("postgres:15");
 *
 *     // spring.datasource.url 등이 자동으로 설정된다
 * }
 * </pre>
 */
@Configuration
public class SpringBoot31DockerComposeConfig {
    // Docker Compose 연동은 spring-boot-docker-compose 의존성 추가만으로 활성화된다.
    // 별도 빈 설정 없이 application.yml의 spring.docker.compose.* 속성으로 제어한다.
}
