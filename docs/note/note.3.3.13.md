# Spring Boot 3.3.13 업그레이드 노트

## 참고 링크

| 구분 | 링크 |
|------|------|
| Release Notes | [Spring Boot 3.3 Release Notes](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.3-Release-Notes) |
| Reference Docs | [Spring Boot 3.3.13 Reference](https://docs.spring.io/spring-boot/docs/3.3.13/reference/html/) |
| API Docs | [Spring Boot 3.3.13 API](https://docs.spring.io/spring-boot/docs/3.3.13/api/) |
| Migration Guide | [Spring Boot 3.3 Migration Guide](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.3-Migration-Guide) |
| Spring Framework | [Spring Framework 6.1 What's New](https://github.com/spring-projects/spring-framework/wiki/What%27s-New-in-Spring-Framework-6.x#whats-new-in-version-61) |
| Structured Logging | [Structured Logging Reference](https://docs.spring.io/spring-boot/docs/3.3.13/reference/html/features.html#features.logging.structured) |
| CDS | [CDS with Spring Boot](https://docs.spring.io/spring-boot/docs/3.3.13/reference/html/deployment.html#deployment.efficient.cds) |

## 변경 개요

| 항목 | 이전 | 이후 |
|------|------|------|
| Spring Boot | 3.2.12 | 3.3.13 |
| Java | 17 | 21 |

## 변경 파일

- `pom.xml`: `spring-boot-starter-parent` 3.2.12 → 3.3.13
- `pom.xml`: `<java.version>17</java.version>` → `<java.version>21</java.version>`
- `build.gradle`: `org.springframework.boot` 플러그인 3.2.12 → 3.3.13
- `build.gradle`: `sourceCompatibility = 17` → `sourceCompatibility = 21`

## Spring Boot 3.3.x 주요 변경사항

- **Java 21 LTS 기본 지원**: Virtual Thread, Structured Concurrency, Record Pattern 등 Java 21 기능 공식 지원
- **Virtual Threads 안정화**: `spring.threads.virtual.enabled=true` 설정이 더 광범위하게 지원됨
  - `@Async`, `@Scheduled`, RabbitMQ/Kafka 리스너 등에도 Virtual Thread 적용 가능
- **Spring Security 6.3**: `@AuthorizationManager` 기반 메서드 보안 강화
- **CDS(Class Data Sharing) 지원**: `spring-boot:process-aot` → `spring-boot:run` 조합으로 JVM 시작 속도 개선
- **Service Connection 강화**: Testcontainers `@ServiceConnection` 지원 범위 확대
- **`@Fallback` 빈**: `@Primary` 대신 폴백용 빈을 지정하는 `@Fallback` 어노테이션 추가

## 확인 결과

| 항목 | 결과 |
|------|------|
| Java 21 컴파일 | 성공 |
| 코드 변경 | 없음 (무변경 통과) |
| 전체 테스트 | 3/3 통과 |

## 빌드 환경

- 빌드 JDK: Java 21 (`21.0.10-amzn`)
- Maven: 3.9.x
- 테스트: 전체 통과

## 다음 단계

**Step 9**: Spring Boot 3.3.13 → 3.4.x (Java 21 유지)
- Spring Security 6.4 변경사항 확인
- Micrometer 1.14 변경사항 확인
- Structured Logging 지원 확인
