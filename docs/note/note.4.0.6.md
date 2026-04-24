# Spring Boot 4.0.6 업그레이드 노트

## 참고 링크

| 구분 | 링크 |
|------|------|
| Release Notes | [Spring Boot 4.0 Release Notes](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-4.0-Release-Notes) |
| Reference Docs | [Spring Boot 4.0 Reference](https://docs.spring.io/spring-boot/reference/) |
| API Docs | [Spring Boot 4.0 API](https://docs.spring.io/spring-boot/api/java/) |
| Migration Guide | [Spring Boot 4.0 Migration Guide](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-4.0-Migration-Guide) |
| Spring Framework | [Spring Framework 7.0 What's New](https://github.com/spring-projects/spring-framework/wiki/What%27s-New-in-Spring-Framework-7.x) |
| Spring Security | [Spring Security 7.0 Migration Guide](https://docs.spring.io/spring-security/reference/migration/index.html) |
| RestClient | [RestClient Reference](https://docs.spring.io/spring-framework/reference/integration/rest-clients.html#rest-restclient) |

## 변경 개요

| 항목 | 이전 | 이후 |
|------|------|------|
| Spring Boot | 3.4.13 | 4.0.6 |
| Java | 21 | 21 |
| Spring Framework | 6.x | 7.0.7 |

## 변경 파일

### 빌드 파일
- `pom.xml`: `spring-boot-starter-parent` 3.4.13 → 4.0.6
- `build.gradle`: `org.springframework.boot` 플러그인 3.4.13 → 4.0.6

### 패키지 이동 (Spring Boot 4.0 모듈 재편)
| 파일 | 변경 내용 |
|------|----------|
| `BootSpringBootApplication.java` | `@ServletComponentScan` import: `boot.web.servlet` → `boot.web.server.servlet.context` |
| `WebMvcConfig.java` | `WebMvcRegistrations` import: `boot.autoconfigure.web.servlet` → `boot.webmvc.autoconfigure` |
| `WebSecurityConfig.java` | `EndpointRequest` import: `boot.actuate.autoconfigure.security.servlet` → `boot.security.autoconfigure.actuate.web.servlet` |

### API 제거 대응
| 파일 | 변경 내용 |
|------|----------|
| `HoneymonApiRestTemplateBuilder.java` | `RestTemplateBuilder` 제거 → `SimpleClientHttpRequestFactory` + `DefaultUriBuilderFactory` + Interceptor로 직접 구성 |
| `RestClientExampleBean.java` | `RestTemplateBuilder` 주입 제거 → `RestClient.create()` 직접 생성, `RestTemplate` → `RestClient` API로 전환 |

## Spring Boot 4.0.x 주요 변경사항

### 모듈 구조 재편
Spring Boot 4.0은 내부 모듈을 대폭 재편했다. 주요 패키지 이동:

| 이전 패키지 | 이후 패키지 | 내용 |
|------------|------------|------|
| `org.springframework.boot.web.servlet.*` | `org.springframework.boot.web.server.servlet.context.*` | `@ServletComponentScan` 등 |
| `org.springframework.boot.autoconfigure.web.servlet.*` | `org.springframework.boot.webmvc.autoconfigure.*` | `WebMvcRegistrations` 등 |
| `org.springframework.boot.actuate.autoconfigure.security.servlet.*` | `org.springframework.boot.security.autoconfigure.actuate.web.servlet.*` | `EndpointRequest` 등 |

### API 제거
- **`RestTemplateBuilder` 완전 제거**: `org.springframework.boot.web.client.RestTemplateBuilder`가 제거됨
  - 대안 1: `RestClient` (Spring Framework 6.1+ 신규 HTTP 클라이언트, 권장)
  - 대안 2: `SimpleClientHttpRequestFactory` + `RestTemplate` 직접 구성
- **`RestClient.Builder` 자동 구성 없음**: Spring Boot 4.0에서 `RestClient.Builder`를 빈으로 자동 등록하지 않음
  → `RestClient.create()` 또는 `RestClient.builder().build()`로 직접 생성

### 기타
- Spring Framework 7.0 기반
- Spring Security 7.x 기반
- Actuator 보안 패키지 재편

## 트러블슈팅

### 1. 패키지 이동으로 인한 컴파일 오류 (4건)
**증상**: `package does not exist` / `cannot find symbol` 오류 (컴파일 단계)

**원인**: Spring Boot 4.0의 모듈 재편으로 `@ServletComponentScan`, `WebMvcRegistrations`, `EndpointRequest` 패키지 변경

**해결**: 각 클래스의 새 패키지로 import 수정

### 2. `RestTemplateBuilder` 제거
**증상**: `package org.springframework.boot.web.client does not exist`

**원인**: Spring Boot 4.0에서 `RestTemplateBuilder` 완전 제거

**해결**: `HoneymonApiRestTemplateBuilder` → `SimpleClientHttpRequestFactory` 직접 사용, `RestClientExampleBean` → `RestClient.create()`로 전환

### 3. `RestClient.Builder` 빈 없음
**증상**: `No qualifying bean of type 'RestClient$Builder' available` (런타임 Context 로딩 실패)

**원인**: Spring Boot 4.0에서 `RestClient.Builder`가 더 이상 자동 구성 빈으로 제공되지 않음

**해결**: 생성자 주입 제거 → `RestClient.create()` 직접 호출

## 확인 결과

| 항목 | 결과 |
|------|------|
| 패키지 이동 대응 | 완료 (3개 파일) |
| `RestTemplateBuilder` 제거 대응 | 완료 (2개 파일) |
| 전체 테스트 | 3/3 통과 |

## 빌드 환경

- 빌드 JDK: Java 21 (`21.0.10-amzn`)
- Maven: 3.9.x
- 테스트: 전체 통과
