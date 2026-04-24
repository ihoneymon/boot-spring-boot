package io.honeymon.boot.springboot.feature.v27;

import org.springframework.context.annotation.Configuration;

/**
 * Spring Boot 2.7 신기능: AutoConfiguration 등록 방식 변경
 *
 * <p>{@code spring.factories}에서 {@code META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports}
 * 파일로 자동구성 등록 방식이 변경됐다.</p>
 *
 * <h3>기존 방식 (Deprecated in 2.7, 제거 예정)</h3>
 * <p>{@code META-INF/spring.factories} 파일에 아래와 같이 등록했다:</p>
 * <pre>
 * org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
 *   com.example.FooAutoConfiguration,\
 *   com.example.BarAutoConfiguration
 * </pre>
 *
 * <h3>신규 방식 (2.7+)</h3>
 * <p>{@code META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports} 파일에 등록:</p>
 * <pre>
 * com.example.FooAutoConfiguration
 * com.example.BarAutoConfiguration
 * </pre>
 *
 * <p>자동구성 클래스에는 {@code @Configuration} 대신 {@code @AutoConfiguration}을 사용하는 것을 권장한다:</p>
 * <pre>
 * {@literal @}AutoConfiguration
 * public class FooAutoConfiguration {
 *     // ...
 * }
 * </pre>
 *
 * <h3>@AutoConfiguration 어노테이션</h3>
 * <p>{@code @AutoConfiguration}은 {@code @Configuration(proxyBeanMethods = false)}를 포함하며,
 * {@code after}, {@code afterNames}, {@code before}, {@code beforeNames} 속성으로
 * 자동구성 순서를 제어할 수 있다.</p>
 *
 * <h3>Deprecated in 2.7</h3>
 * <ul>
 *   <li>{@code spring.factories}의 {@code EnableAutoConfiguration} 등록 방식 — 3.0에서 제거 예정</li>
 *   <li>{@code @AutoConfigureBefore} / {@code @AutoConfigureAfter} → {@code @AutoConfiguration(before/after)} 권장</li>
 * </ul>
 *
 * <h3>Spring MVC Actuator Info Endpoint 변경</h3>
 * <p>{@code /actuator/info}에서 OS 정보, Java 정보를 노출하는 기능이 기본 비활성화됐다.
 * 필요한 경우 아래 설정으로 활성화한다:</p>
 * <pre>
 * management:
 *   info:
 *     os:
 *       enabled: true
 *     java:
 *       enabled: true
 *     env:
 *       enabled: true
 * </pre>
 */
@Configuration
public class SpringBoot27AutoConfigRegistrationConfig {
    // spring.factories 기반 자동구성 등록은 deprecated 되었다.
    // 신규 라이브러리/스타터 개발 시 AutoConfiguration.imports 방식을 사용하라.
}
