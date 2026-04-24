# Boot Spring Boot 유지보수 정책

새로운 Spring Boot 버전이 출시될 때마다 이 문서에 기술된 절차에 따라
코드 예제와 책 챕터를 함께 업데이트한다.

---

## 연관 저장소

| 저장소 | 역할 | 브랜치 |
|--------|------|--------|
| [boot-spring-boot](https://github.com/ihoneymon/boot-spring-boot) | 예제 코드 | `upgrade/spring-boot-4x` |
| [boot-spring-boot-book](https://github.com/ihoneymon/boot-spring-boot-book) | 도서 본문 | `upgrade/spring-boot-4x` |

두 저장소는 **동일한 버전 태그**를 공유한다. `git checkout v3.2.12`로 체크아웃하면
코드와 책 내용이 해당 버전 시점으로 함께 맞춰진다.

---

## 브랜치 전략

```
upgrade/spring-boot-4x          ← 독자가 체크아웃하는 대표 브랜치 (origin에 push)
upgrade/spring-boot-4x-with-examples  ← 실제 작업 브랜치 (로컬 워크트리)
```

작업은 항상 `upgrade/spring-boot-4x-with-examples`에서 진행하고,
완료 후 `upgrade/spring-boot-4x`로 force-push한다.

---

## 작업 흐름 (새 버전 출시 시)

### 사전 준비

```bash
# 기존 마이그레이션 커밋 SHA를 먼저 확보한다
# (이전에 upgrade/spring-boot-4x 브랜치에서 작업된 마이그레이션 커밋)
git -C projects/boot-spring-boot/main log upgrade/spring-boot-4x --oneline | head -5
```

### Step 1. 마이그레이션 커밋 cherry-pick (코드 repo)

```bash
git -C projects/boot-spring-boot/worktrees/upgrade-with-examples cherry-pick <MIGRATION_SHA>
```

cherry-pick 대상은 의존성 버전 업그레이드, 코드 마이그레이션, 빌드 파일 변경이
포함된 커밋이다. 충돌 시 해결 후 `git cherry-pick --continue`.

### Step 2. 신기능 예제 파일 작성

```
src/main/java/io/honeymon/boot/springboot/feature/v{MAJOR}{MINOR}/
└── SpringBoot{MAJOR}{MINOR}{신기능키워드}Config.java
```

**파일 작성 규칙:**

- 클래스명: `SpringBoot{버전숫자}{핵심기능}Config` (예: `SpringBoot32RestClientConfig`)
- `@Configuration` 어노테이션 필수
- Javadoc에 아래 항목을 반드시 포함한다:
  - `<h3>` 단위로 주요 신기능 설명
  - `application.yml` 설정 예시 (`<pre>` 블록)
  - 코드 사용 예시 (`<pre>` 블록)
  - `<h3>Deprecated in {버전}</h3>` — deprecated된 항목과 대체 방법
- 실제 동작 코드는 최소화하고, 설명 위주로 작성한다
- 여러 신기능이 있으면 하나의 파일에 Javadoc으로 모두 설명한다

**예시:**
```java
/**
 * Spring Boot 3.2 신기능: RestClient, JdbcClient, Virtual Threads
 *
 * <h3>RestClient — 동기 HTTP 클라이언트 (신규)</h3>
 * <p>설명...</p>
 * <pre>
 * // 사용 예시
 * </pre>
 *
 * <h3>Deprecated in 3.2</h3>
 * <ul>
 *   <li>RestTemplate 자동구성 → RestClient 사용 권장</li>
 * </ul>
 */
@Configuration
public class SpringBoot32RestClientConfig {
    // 핵심 빈 등록 예시만 포함 (없으면 주석으로 대체)
}
```

### Step 3. 코드 repo 커밋 + 태그

```bash
git -C projects/boot-spring-boot/worktrees/upgrade-with-examples \
  add src/main/java/io/honeymon/boot/springboot/feature/v{버전숫자}/

git -C projects/boot-spring-boot/worktrees/upgrade-with-examples \
  commit -m "feat: Spring Boot {버전} 신기능 예제 추가

- {기능1} 설명
- {기능2} 설명
- {Deprecated 항목} deprecated 안내"

git -C projects/boot-spring-boot/worktrees/upgrade-with-examples \
  tag -f v{X.Y.Z} HEAD
```

> **주의:** `git -C` 뒤에 반드시 **하드코딩된 절대 경로**를 사용한다.
> 셸 변수(`$VAR`)를 쓰면 branch-guard 훅이 워크스페이스 main 브랜치로 오인하여 차단한다.

### Step 4. 책 챕터 업데이트

신기능 종류에 따라 아래 기준으로 챕터를 선택한다.

| 신기능 분류 | 추가 위치 |
|------------|----------|
| ApplicationStartup, 배너, 시작 이벤트 | `chap03/03-01-spring-application.asc` |
| 외부화 구성, config.import | `chap03/03-02-externalize-configuration.asc` |
| Profile, Profile Groups | `chap03/03-03-profiles.asc` |
| Logging, Structured Logging | `chap03/03-04-logging.asc` |
| SQL 초기화, 데이터소스 | `chap03/03-07-using-sql-database.asc` |
| REST 클라이언트 (RestTemplate, RestClient) | `chap03/03-10-call-rest-service-resttemplate.asc` |
| WebClient, @HttpExchange | `chap03/03-11-call-rest-service-webclient.asc` |
| 테스트, Testcontainers, MockMvcTester | `chap03/03-13-test.asc` |
| AutoConfiguration 등록 방식 | `chap03/03-15-create-auto-configuration.asc` |
| Health, Actuator, Probes | `chap04/04-01-enable-production-ready.asc` |

**챕터 작성 규칙:**

- 기존 챕터 마지막에 `==== {기능명} (Spring Boot {버전}+)` 섹션으로 추가한다
- 섹션 앵커: `[[boot-features-{기능키워드}]]`
- deprecated 항목은 반드시 `[WARNING]` 블록으로 강조한다
- 설정 예시는 `[source,yaml]` 코드 블록으로 작성한다
- 코드 예시는 `[source,java]` 코드 블록으로 작성한다
- 버전 비교가 필요하면 "이전/이후" 코드 블록을 나란히 배치한다

**예시:**
```asciidoc
[[boot-features-rest-client-3-2]]
==== RestClient — 동기 HTTP 클라이언트 (Spring Boot 3.2+)

설명 문장...

[source,java]
----
// 코드 예시
----

[WARNING]
====
deprecated 항목 안내
====
```

### Step 5. 책 repo 커밋 + 태그

```bash
git -C projects/boot-spring-boot-book/worktrees/upgrade \
  add book/chap0{N}/{파일명}.asc

git -C projects/boot-spring-boot-book/worktrees/upgrade \
  commit -m "docs: Spring Boot {버전} 신기능 및 변경사항 추가

- {기능1} 설명 추가
- {기능2} 설명 추가
- {Deprecated 항목} deprecated 안내"

git -C projects/boot-spring-boot-book/worktrees/upgrade \
  tag -f v{X.Y.Z} HEAD
```

### Step 6. 업그레이드 노트 작성

`docs/note/note.{X.Y.Z}.md` 파일을 생성한다.

**필수 섹션:**

```markdown
# Spring Boot {X.Y.Z} 업그레이드 노트

## 참고 링크

| 구분 | 링크 |
|------|------|
| Release Notes | [Spring Boot {X.Y} Release Notes](...wiki URL...) |
| Reference Docs | [Spring Boot {X.Y.Z} Reference](...docs URL...) |
| API Docs | [Spring Boot {X.Y.Z} API](...api URL...) |
| Migration Guide | [Spring Boot {X.Y} Migration Guide](...wiki URL...) |
| Spring Framework | [...] |

## 변경 개요

(버전 비교 표: Spring Boot, Java, 주요 의존성)

## 변경 파일

(빌드 파일, 소스 파일 변경 목록)

## Spring Boot {X.Y}.x 주요 변경사항

(신기능 및 deprecated 항목 설명)

## 트러블슈팅  ← 문제가 발생한 경우에만

(오류 증상 → 원인 → 해결 방법)

## 확인 결과

(항목별 결과 표)

## 빌드 환경

(JDK 버전, Maven/Gradle 버전)
```

**참고 링크 URL 패턴:**

| 구분 | URL 패턴 |
|------|----------|
| Release Notes (Wiki) | `https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-{X.Y}-Release-Notes` |
| Reference Docs (2.x~3.x) | `https://docs.spring.io/spring-boot/docs/{X.Y.Z}/reference/html/` |
| Reference Docs (4.x+) | `https://docs.spring.io/spring-boot/reference/` |
| API Docs (2.x~3.x) | `https://docs.spring.io/spring-boot/docs/{X.Y.Z}/api/` |
| Migration Guide | `https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-{X.Y}-Migration-Guide` |
| Spring Framework 5.x | `https://github.com/spring-projects/spring-framework/wiki/What%27s-New-in-Spring-Framework-5.x` |
| Spring Framework 6.x | `https://github.com/spring-projects/spring-framework/wiki/What%27s-New-in-Spring-Framework-6.x` |
| Spring Framework 7.x | `https://github.com/spring-projects/spring-framework/wiki/What%27s-New-in-Spring-Framework-7.x` |

### Step 7. 원격 Push

```bash
# 코드 repo: 작업 브랜치를 upgrade/spring-boot-4x로 force-push
git -C projects/boot-spring-boot/main push --force origin \
  upgrade/spring-boot-4x-with-examples:upgrade/spring-boot-4x

# 코드 repo: 새 태그 push
git -C projects/boot-spring-boot/main push origin v{X.Y.Z}

# 책 repo: 브랜치 push
git -C projects/boot-spring-boot-book/worktrees/upgrade push origin upgrade/spring-boot-4x

# 책 repo: 새 태그 push
git -C projects/boot-spring-boot-book/worktrees/upgrade push origin v{X.Y.Z}
```

### Step 8. README 업데이트 + PR

`README.asc`의 버전 요약 표에 새 버전 행을 추가한다.

```asciidoc
|{X.Y.Z}
|{주요 신기능 목록}
|`feature/v{XY}/{ClassName}.java`
|link:docs/note/note.{X.Y.Z}.md[note.{X.Y.Z}]
```

커밋 후 PR을 생성한다. PR은 직접 머지하지 않고 링크를 확인 후 수동 머지한다.

---

## 버전 분류 기준

### Major 버전 (X.0.0)

- Spring Framework 메이저 버전 업
- Java 최소 요구 버전 변경
- 패키지 네임스페이스 변경 (예: `javax` → `jakarta`)
- API 대규모 제거
- 마이그레이션 가이드가 별도 문서로 제공됨

**작업 범위 확대:** 빌드 파일, import 전체 교체, 제거된 API 대체 코드 필수 포함.

### Minor 버전 (X.Y.0)

- 신규 기능 추가 (RestClient, Structured Logging 등)
- 기존 기능 deprecated 선언
- 의존성 관리 버전 변경 (Lombok, H2 등)

**작업 범위:** 신기능 예제 파일 1개 + 해당 챕터 섹션 추가.

### Patch 버전 (X.Y.Z)

- 버그 수정, 보안 패치
- 예제 코드 변경 불필요
- 빌드 파일 버전만 bump하고 태그만 이동

---

## 태그 정책

- 태그명: `v{X.Y.Z}` (예: `v3.2.12`)
- 코드 repo 태그: 마이그레이션 커밋 + 신기능 예제 커밋이 모두 포함된 HEAD에 태그
- 책 repo 태그: 해당 버전 챕터 업데이트 커밋이 포함된 HEAD에 태그
- 기존 태그 이동 필요 시 `-f` 플래그 사용 후 `push --force --tags` 또는 개별 태그 force-push

---

## 파일/디렉토리 구조

```
boot-spring-boot/
├── src/main/java/.../feature/
│   ├── v24/SpringBoot24StartupConfig.java
│   ├── v25/SpringBoot25SqlInitConfig.java
│   ├── v26/SpringBoot26HealthProbeConfig.java
│   ├── ...
│   └── v{XY}/{ClassName}Config.java       ← 신규 버전 추가 위치
├── docs/
│   ├── MAINTENANCE.md                      ← 이 파일
│   └── note/
│       ├── note.2.4.13.md
│       ├── ...
│       └── note.{X.Y.Z}.md                ← 신규 버전 추가 위치
└── README.asc                              ← 버전 요약 표 업데이트 필요
```

```
boot-spring-boot-book/
└── book/
    ├── chap03/
    │   ├── 03-01-spring-application.asc
    │   ├── ...
    │   └── 03-15-create-auto-configuration.asc
    └── chap04/
        └── 04-01-enable-production-ready.asc
```

---

## 체크리스트

새 버전 작업 시 아래 항목을 순서대로 확인한다.

- [ ] 마이그레이션 커밋 cherry-pick 완료
- [ ] `feature/v{XY}/` 예제 파일 작성 (Javadoc: 신기능 + deprecated + 설정 예시)
- [ ] 코드 repo 커밋 (`feat: Spring Boot {버전} 신기능 예제 추가`)
- [ ] 코드 repo 태그 `v{X.Y.Z}` 설정
- [ ] 책 챕터 해당 섹션 추가 (WARNING/NOTE 블록 포함)
- [ ] 책 repo 커밋 (`docs: Spring Boot {버전} 신기능 및 변경사항 추가`)
- [ ] 책 repo 태그 `v{X.Y.Z}` 설정
- [ ] `docs/note/note.{X.Y.Z}.md` 작성 (참고 링크 포함)
- [ ] `README.asc` 버전 요약 표에 행 추가
- [ ] 코드 repo force-push (`upgrade/spring-boot-4x`)
- [ ] 책 repo push (`upgrade/spring-boot-4x`)
- [ ] 새 태그 push (양쪽 repo)
- [ ] PR 생성 (코드 + 책 각각)
