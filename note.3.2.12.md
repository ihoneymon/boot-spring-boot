# Spring Boot 3.2.12 업그레이드 노트

## 변경 개요

| 항목 | 이전 | 이후 |
|------|------|------|
| Spring Boot | 3.1.12 | 3.2.12 |
| Java | 17 | 17 |

## 변경 파일

- `pom.xml`: `spring-boot-starter-parent` 3.1.12 → 3.2.12
- `build.gradle`: `org.springframework.boot` 플러그인 3.1.12 → 3.2.12

## Spring Boot 3.2.x 주요 변경사항

- **Virtual Threads (Project Loom)**: `spring.threads.virtual.enabled=true` 설정으로 활성화 가능
  - Tomcat, Jetty 등 웹 서버의 요청 처리 스레드를 Virtual Thread로 전환
  - 본 프로젝트: 미적용 (선택적 기능)
- **`RestClient` 도입**: `RestTemplate`을 대체하는 새로운 동기 HTTP 클라이언트
  - 빌더 패턴 기반의 플루언트 API
  - `WebClient`와 유사한 DSL 제공
  - 본 프로젝트: 기존 `RestTemplate` 사용 유지 (deprecated 아님)
- **`JdbcClient` 도입**: `JdbcTemplate`을 대체하는 플루언트 JDBC API (본 프로젝트 미사용)
- **SSL Bundles**: SSL 인증서 설정 추상화 (`spring.ssl.bundle.*`)
- **Spring Security 6.2**: `authorizeHttpRequests` Lambda DSL 강화
- **`@ConditionalOnThreading`**: Virtual Thread 활성화 여부 기반 조건부 빈 등록

## 확인 결과

| 항목 | 결과 |
|------|------|
| 코드 변경 | 없음 (무변경 통과) |
| 전체 테스트 | 3/3 통과 |

## 빌드 환경

- 빌드 JDK: Java 17 (`17.0.18-amzn`)
- Maven: 3.9.x
- 테스트: 전체 통과

## 다음 단계

**Step 8**: Spring Boot 3.2.12 → 3.3.x (Java 21)
- Java 21로 변경 필요 (Spring Boot 3.3부터 Java 21 권장)
- GraalVM Native Image 지원 강화
- Structured Concurrency / Virtual Thread 안정화
- Spring Security 6.3 변경사항 확인
