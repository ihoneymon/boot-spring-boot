# Spring Boot 3.4.13 업그레이드 노트

## 변경 개요

| 항목 | 이전 | 이후 |
|------|------|------|
| Spring Boot | 3.3.13 | 3.4.13 |
| Java | 21 | 21 |

## 변경 파일

- `pom.xml`: `spring-boot-starter-parent` 3.3.13 → 3.4.13
- `build.gradle`: `org.springframework.boot` 플러그인 3.3.13 → 3.4.13

## Spring Boot 3.4.x 주요 변경사항

- **Structured Logging**: JSON 형식 로그 출력 지원 (`logging.structured.format.console=ecs` 등)
- **MockMvcTester 도입**: `MockMvc`를 AssertJ 스타일로 감싼 새로운 테스트 API
- **`@Fallback` 빈 안정화**: 3.3에서 도입된 폴백 빈 기능 강화
- **Spring Security 6.4**: `@PreAuthorize`, `@PostAuthorize` 메서드 보안 성능 개선
- **`spring.docker.compose.lifecycle-management`**: Docker Compose 연동 생명주기 옵션 세분화
- **`@ConditionalOnMissingBean` 개선**: 제네릭 타입 기반 조건 처리 강화
- **Micrometer 1.14**: Observation API 개선

## 확인 결과

| 항목 | 결과 |
|------|------|
| 코드 변경 | 없음 (무변경 통과) |
| 전체 테스트 | 3/3 통과 |

## 빌드 환경

- 빌드 JDK: Java 21 (`21.0.10-amzn`)
- Maven: 3.9.x
- 테스트: 전체 통과

## 다음 단계

**Step 10**: Spring Boot 3.4.13 → 4.0.x (Java 21 유지)
- Spring Boot 4.0 마이그레이션 가이드 확인 필요
- Jakarta EE 11 전환 가능성 확인
- Spring Framework 7.x 변경사항 반영
