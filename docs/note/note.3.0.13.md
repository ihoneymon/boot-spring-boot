# Spring Boot 3.0.13 업그레이드 노트

## 참고 링크

| 구분 | 링크 |
|------|------|
| Release Notes | [Spring Boot 3.0 Release Notes](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.0-Release-Notes) |
| Reference Docs | [Spring Boot 3.0.13 Reference](https://docs.spring.io/spring-boot/docs/3.0.13/reference/html/) |
| API Docs | [Spring Boot 3.0.13 API](https://docs.spring.io/spring-boot/docs/3.0.13/api/) |
| Migration Guide | [Spring Boot 3.0 Migration Guide](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.0-Migration-Guide) |
| Spring Framework | [Spring Framework 6.0 What's New](https://github.com/spring-projects/spring-framework/wiki/What%27s-New-in-Spring-Framework-6.x) |
| Spring Security | [Spring Security 6.0 Migration Guide](https://docs.spring.io/spring-security/reference/6.0/migration/index.html) |
| Jakarta EE 9 | [Jakarta EE 9 Specification](https://jakarta.ee/release/9/) |

## 변경 개요

| 항목 | 이전 | 이후 |
|------|------|------|
| Spring Boot | 2.7.18 | 3.0.13 |
| Java | 17 | 17 |
| Jakarta EE | 8 (javax.*) | 9 (jakarta.*) |

## 변경 파일

### 빌드 파일
- `pom.xml`: `spring-boot-starter-parent` 2.7.18 → 3.0.13
- `build.gradle`: `org.springframework.boot` 플러그인 2.7.18 → 3.0.13
- `build.gradle`: `ext["h2.version"] = "1.4.199"` 제거 (Spring Boot 3.0 관리 버전 사용)

### Jakarta EE 9 네임스페이스 전환 (8개 파일)
| 파일 | 변경 내용 |
|------|----------|
| `ExampleProperties.java` | `javax.annotation` → `jakarta.annotation` |
| `HelloFilter.java` | `javax.servlet` → `jakarta.servlet` |
| `HelloServlet.java` | `javax.servlet` → `jakarta.servlet` |
| `AttrListener.java` | `javax.servlet` → `jakarta.servlet` |
| `BootService.java` | `javax.annotation` → `jakarta.annotation` |
| `User.java` | `javax.persistence` → `jakarta.persistence` |
| `ViewExceptionHandler.java` | `javax.servlet` → `jakarta.servlet` |
| `RestResponseEntityExceptionHandler.java` | `javax.validation` → `jakarta.validation` |

### 설정/구성 변경
- `WebSecurityConfig.java`: `WebSecurityConfigurerAdapter` 제거 → `SecurityFilterChain` 빈 방식으로 재작성
- `application.yml`: `endpoint.httptrace.enabled` → `endpoint.httpexchanges.enabled`

## Spring Boot 3.0.x 주요 변경사항

- **Jakarta EE 9 전환**: 모든 `javax.*` 패키지를 `jakarta.*`로 변경 (필수)
- **WebSecurityConfigurerAdapter 완전 제거**: `SecurityFilterChain` 빈 방식 사용
- **spring.factories AutoConfiguration 제거**: `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports` 파일 사용
  - 본 프로젝트: `spring.factories`에 `ApplicationListener`만 등록 → 3.0에서도 여전히 유효
- **httptrace → httpexchanges**: `/actuator/httptrace` 엔드포인트가 `/actuator/httpexchanges`로 변경
- **Hibernate 6**: 일부 JPQL 쿼리 문법 변경 가능성 (본 프로젝트 영향 없음)

## WebSecurityConfig 변경 내용

```java
// 이전 (Spring Boot 2.x)
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("springboot").password("password").roles("USER");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatcher(EndpointRequest.toAnyEndpoint())
            .authorizeRequests().antMatchers("/greeting").anonymous();
    }
}

// 이후 (Spring Boot 3.x)
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher(EndpointRequest.toAnyEndpoint())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/greeting").anonymous()
                .anyRequest().authenticated());
        return http.build();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        var user = User.withUsername("springboot").password(encoder.encode("password")).roles("USER").build();
        return new InMemoryUserDetailsManager(user);
    }
}
```

## 확인 결과

| 항목 | 결과 |
|------|------|
| Jakarta EE 9 전환 | 완료 (8개 파일) |
| WebSecurityConfig 재작성 | 완료 |
| httptrace → httpexchanges | 완료 |
| 전체 테스트 | 3/3 통과 |

## 빌드 환경

- 빌드 JDK: Java 17 (`17.0.18-amzn`)
- Maven: 3.9.x
- 테스트: 전체 통과

## 다음 단계

**Step 6**: Spring Boot 3.0.13 → 3.1.12
- Spring Security 6.1 변경사항 확인
- `spring.security.oauth2` 변경사항 확인 (해당 시)
- Micrometer 1.11 변경사항 확인
