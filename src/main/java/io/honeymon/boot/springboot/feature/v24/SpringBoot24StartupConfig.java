package io.honeymon.boot.springboot.feature.v24;

import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Boot 2.4 신기능: ApplicationStartup 추적
 *
 * <p>Spring Boot 2.4부터 애플리케이션 시작 단계별 소요 시간을 측정할 수 있다.
 * {@link BufferingApplicationStartup}을 SpringApplication에 설정하면
 * {@code /actuator/startup} 엔드포인트를 통해 각 단계의 성능 정보를 확인할 수 있다.</p>
 *
 * <p>활성화 방법 (BootSpringBootApplication.main):
 * <pre>
 * SpringApplication app = new SpringApplication(BootSpringBootApplication.class);
 * app.setApplicationStartup(new BufferingApplicationStartup(2048));
 * app.run(args);
 * </pre>
 * </p>
 *
 * <h3>spring.config.import (2.4 신기능)</h3>
 * <p>추가 설정 파일을 임포트할 수 있다:</p>
 * <pre>
 * spring:
 *   config:
 *     import:
 *       - classpath:feature/v24-extra.yml    # 클래스패스 파일
 *       - optional:file:./custom.yml         # 선택적 외부 파일
 *       - configtree:/etc/config/            # ConfigTree (쿠버네티스 시크릿 등)
 * </pre>
 *
 * <h3>프로파일 그룹 (Profile Groups, 2.4 신기능)</h3>
 * <pre>
 * spring:
 *   profiles:
 *     group:
 *       production: prod,actuator   # production 활성화 시 prod + actuator도 활성화
 * </pre>
 *
 * <h3>Deprecated in 2.4</h3>
 * <ul>
 *   <li>{@code spring.profiles} → {@code spring.config.activate.on-profile} 로 변경</li>
 *   <li>{@code SpringApplicationBuilder.profiles()} → {@code setAdditionalProfiles()} 사용</li>
 * </ul>
 */
@Configuration
public class SpringBoot24StartupConfig {

    @Bean
    public BufferingApplicationStartup bufferingApplicationStartup() {
        return new BufferingApplicationStartup(2048);
    }
}
