# Spring Boot 2.6.14 업그레이드 노트

## 변경 개요

| 항목 | 이전 | 이후 |
|------|------|------|
| Spring Boot | 2.5.14 | 2.6.14 |
| Java | 11 | 11 |

## 변경 파일

- `pom.xml`: `spring-boot-starter-parent` 2.5.14 → 2.6.14
- `build.gradle`: `org.springframework.boot` 플러그인 2.5.14 → 2.6.14

## Spring Boot 2.6.x 주요 변경사항

- **`spring.profiles` 완전 제거**: Step 2에서 선제 마이그레이션 완료 → 영향 없음
- **순환 의존성 기본 금지**: `BeanCurrentlyInCreationException` 발생 시
  `spring.main.allow-circular-references=true` 로 임시 허용 가능 (본 프로젝트 해당 없음)
- **PathPattern 기반 URL 매칭 기본 전환**: suffix pattern matching(`/path.*`) 제거
  → `spring.mvc.pathmatch.use-suffix-pattern` 속성 제거됨 (본 프로젝트 해당 없음)
- **Actuator**: `management.metrics.export.<product>` deprecated
  → `management.<product>.metrics.export` 으로 이전 권고 (본 프로젝트 사용 없음)
- **Spring MVC**: 인터페이스의 `@RequestMapping`만으로 컨트롤러 감지 불가

## 확인 결과

| 항목 | 결과 |
|------|------|
| 순환 의존성 | 없음 |
| suffix pattern 사용 | 없음 |
| `spring.profiles` 잔재 | 없음 (Step 2에서 처리) |
| 전체 테스트 | 3/3 통과 |

## 빌드 환경

- 빌드 JDK: Java 11 (`11.0.30-amzn`)
- Maven: 3.9.x
- 테스트: 전체 통과

## 다음 단계

**Step 4**: Spring Boot 2.6.14 → 2.7.18
- Java 17로 변경 (Spring Boot 3.x 진입 전 사전 검증)
- `WebSecurityConfigurerAdapter` deprecated 대응 준비
- `spring.factories` AutoConfiguration 등록 방식 변경 예고 확인
