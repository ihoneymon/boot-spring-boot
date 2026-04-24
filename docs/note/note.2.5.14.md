# Spring Boot 2.5.14 업그레이드 노트

## 참고 링크

| 구분 | 링크 |
|------|------|
| Release Notes | [Spring Boot 2.5 Release Notes](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.5-Release-Notes) |
| Reference Docs | [Spring Boot 2.5.14 Reference](https://docs.spring.io/spring-boot/docs/2.5.14/reference/html/) |
| API Docs | [Spring Boot 2.5.14 API](https://docs.spring.io/spring-boot/docs/2.5.14/api/) |
| Migration Guide | [Spring Boot 2.5 Migration Guide](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.5-Release-Notes#deprecations-in-spring-boot-25) |
| Spring Framework | [Spring Framework 5.3 What's New](https://github.com/spring-projects/spring-framework/wiki/What%27s-New-in-Spring-Framework-5.x#whats-new-in-version-53) |

## 변경 개요

| 항목 | 이전 | 이후 |
|------|------|------|
| Spring Boot | 2.4.13 | 2.5.14 |
| Java (소스/빌드) | 1.8 / 11 | 11 / 11 |

## 변경 파일

- `pom.xml`
  - `spring-boot-starter-parent` 2.4.13 → 2.5.14
  - `<java.version>1.8</java.version>` → `<java.version>11</java.version>`
- `build.gradle`
  - `org.springframework.boot` 플러그인 2.4.13 → 2.5.14
  - `sourceCompatibility = 1.8` → `sourceCompatibility = 11`
- `src/main/resources/application.yml`
  - 구식 프로파일 선언 방식 전면 마이그레이션 (4개 프로파일)
  ```yaml
  # 이전 (2.4에서 deprecated, 2.6에서 제거)
  spring:
    profiles: local

  # 이후
  spring:
    config:
      activate:
        on-profile: local
  ```

## Spring Boot 2.5.x 주요 변경사항

- **`spring.profiles` 방식 지원 종료 예고**: 2.6에서 완전 제거 → 이번 단계에서 선제 마이그레이션
- **SQL 초기화 통합**: `spring.datasource.schema/data` → `spring.sql.init.schema-locations/data-locations`
  (본 프로젝트에서는 SQL 초기화 미사용으로 해당 없음)
- **Gradle 플러그인**: `bootJar`, `bootWar` 빌드 시 레이어드 JAR 기본 활성화
- **Actuator**: `/actuator/startup` 엔드포인트 추가

## 빌드 환경

- 빌드 JDK: Java 11 (`11.0.30-amzn`)
- Maven: 3.9.x
- 테스트: 전체 통과

## 다음 단계

**Step 3**: Spring Boot 2.5.14 → 2.6.14
- 순환 의존성(circular dependency) 기본 비활성화 대응 확인
- `PathMatchingResourcePatternResolver` 관련 변경 확인
