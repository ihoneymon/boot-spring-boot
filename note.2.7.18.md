# Spring Boot 2.7.18 업그레이드 노트

## 변경 개요

| 항목 | 이전 | 이후 |
|------|------|------|
| Spring Boot | 2.6.14 | 2.7.18 |
| Java | 11 | 17 |
| Lombok | 1.18.16 (고정) | 1.18.24 (Spring Boot 관리) |
| H2 | 1.4.x | 2.1.214 (Spring Boot 관리) |

## 변경 파일

- `pom.xml`: `spring-boot-starter-parent` 2.6.14 → 2.7.18
- `pom.xml`: `<java.version>11</java.version>` → `<java.version>17</java.version>`
- `pom.xml`: `<lombok.version>1.18.16</lombok.version>` 제거 (Spring Boot 관리 버전 사용)
- `build.gradle`: `org.springframework.boot` 플러그인 2.6.14 → 2.7.18
- `build.gradle`: `sourceCompatibility = 11` → `sourceCompatibility = 17`
- `User.java`: `@Table(name = "users")` 추가 (H2 2.x 예약어 충돌 대응)

## Spring Boot 2.7.x 주요 변경사항

- **Auto-configuration 등록 방식 변경 예고**
  - `META-INF/spring.factories`의 `EnableAutoConfiguration` 항목은 deprecated
  - Spring Boot 3.0에서 제거 예정 → `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`로 이전 필요
  - 본 프로젝트: custom AutoConfiguration 없음 → 영향 없음
- **`WebSecurityConfigurerAdapter` deprecated 경고**
  - Spring Boot 3.0에서 완전 제거 예정
  - 본 프로젝트: `SecurityConfig`에서 사용 중 → Step 5(3.0 진입)에서 수정 필요
- **Spring MVC**: `PathMatchingConfigurer` → `PathPatternParser` 기본값 변경 완료
- **Spring Security 5.7**: `WebSecurityConfigurerAdapter` deprecated

## 트러블슈팅

### 1. Lombok 1.18.16 + Java 17 호환성 오류

**증상**: `IllegalAccessError: class lombok.javac.apt.LombokProcessor cannot access class com.sun.tools.javac.processing.JavacProcessingEnvironment`

**원인**: Lombok 1.18.16은 Java 17의 강화된 모듈 접근 제한과 호환되지 않음

**해결**: `pom.xml`의 고정 Lombok 버전 제거 → Spring Boot 2.7.18 관리 버전(1.18.24) 사용

### 2. H2 2.x 예약어 충돌 (`user` 테이블)

**증상**: `Syntax error in SQL statement "insert into [*]user ..."` — H2 2.x에서 `USER`가 예약어로 처리됨

**원인**: Spring Boot 2.7.x는 H2 2.1.214를 관리하며, H2 2.x부터 `USER`는 예약어

**해결**: `User` 엔티티에 `@Table(name = "users")` 추가로 테이블명 변경

## 확인 결과

| 항목 | 결과 |
|------|------|
| Java 17 컴파일 | 성공 |
| `WebSecurityConfigurerAdapter` deprecated 경고 | 있음 (Step 5에서 수정) |
| H2 2.x `user` 예약어 충돌 | 수정 완료 (`@Table(name = "users")`) |
| 전체 테스트 | 3/3 통과 |

## 빌드 환경

- 빌드 JDK: Java 17 (`17.0.18-amzn`)
- Maven: 3.9.x
- 테스트: 전체 통과

## 다음 단계

**Step 5**: Spring Boot 2.7.18 → 3.0.13
- `javax.*` → `jakarta.*` 네임스페이스 전환 (Jakarta EE 9)
- `WebSecurityConfigurerAdapter` 제거 → `SecurityFilterChain` 빈 방식으로 재작성
- `spring.factories` AutoConfiguration → `AutoConfiguration.imports` 이전
- `management.endpoints.web.exposure.include: httptrace` → `httpexchanges`
