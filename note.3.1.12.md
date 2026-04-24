# Spring Boot 3.1.12 업그레이드 노트

## 변경 개요

| 항목 | 이전 | 이후 |
|------|------|------|
| Spring Boot | 3.0.13 | 3.1.12 |
| Java | 17 | 17 |

## 변경 파일

- `pom.xml`: `spring-boot-starter-parent` 3.0.13 → 3.1.12
- `build.gradle`: `org.springframework.boot` 플러그인 3.0.13 → 3.1.12

## Spring Boot 3.1.x 주요 변경사항

- **Docker Compose 지원 추가**: `spring-boot-docker-compose` 의존성으로 로컬 개발 시 자동 Docker Compose 기동 지원 (본 프로젝트 미사용)
- **Testcontainers 지원 강화**: `@ServiceConnection`으로 테스트 컨테이너 자동 연결 (본 프로젝트 미사용)
- **Spring Security 6.1**: `authorizeHttpRequests` Lambda DSL 기반 설정이 권장 방식으로 고착화 → Step 5에서 이미 적용 완료
- **`application.properties` 기반 ConnectionDetails 추상화** 도입: DB, Redis 등 연결 설정을 추상화하는 `ConnectionDetails` 인터페이스 추가
- **Auto-configuration 어노테이션 간소화**: `@AutoConfiguration(after=...)` 속성 추가

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

**Step 7**: Spring Boot 3.1.12 → 3.2.x
- Virtual Thread(Project Loom) 지원 확인
- `RestClient` 도입 (RestTemplate 대체 신규 API)
- Spring Security 6.2 변경사항 확인
