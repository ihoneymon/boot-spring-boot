# Spring Boot 2.4.13 업그레이드 노트

## 변경 개요

| 항목 | 이전 | 이후 |
|------|------|------|
| Spring Boot | 2.4.1 | 2.4.13 |
| Java (빌드) | 1.8 | 11 |
| Lombok | 1.18.16 | 1.18.16 |

## 변경 파일

- `pom.xml`
  - `spring-boot-starter-parent` 2.4.1 → 2.4.13
  - `git-commit-id-plugin`에 `<useNativeGit>true</useNativeGit>` 추가 (git worktree 환경 지원)
- `build.gradle`
  - `org.springframework.boot` 플러그인 2.4.1 → 2.4.13

## Spring Boot 2.4.x 주요 특징 (참고)

- 버전 표기 방식 변경: `2.4.0.RELEASE` → `2.4.0` (RELEASE 접미사 제거)
- **Config Data API 개편**: `spring.profiles` 방식 deprecated → `spring.config.activate.on-profile` 권장
  - `application.yml` 다중 문서 프로파일 선언 방식이 변경됨 (Step 2에서 대응)
- `spring-boot-starter-validation` 이 `spring-boot-starter-web` 에서 분리됨 (2.3.x부터)
- 볼륨(Volume) 기반 Docker 이미지 레이어링 지원

## 빌드 환경

- 빌드 JDK: Java 11 (`11.0.30-amzn`)
- Maven: 3.9.x
- 테스트: 전체 통과

## 다음 단계

**Step 2**: Spring Boot 2.4.13 → 2.5.14
- `application.yml`: `spring: profiles: xxx` → `spring: config: activate: on-profile: xxx` 마이그레이션
- Java 소스 호환 버전 1.8 → 11 변경
