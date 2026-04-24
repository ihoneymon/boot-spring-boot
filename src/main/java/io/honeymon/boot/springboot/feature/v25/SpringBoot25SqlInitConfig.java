package io.honeymon.boot.springboot.feature.v25;

import org.springframework.boot.autoconfigure.sql.init.SqlInitializationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Boot 2.5 신기능: SQL 초기화 속성 개편
 *
 * <p>Spring Boot 2.5에서 SQL 데이터베이스 초기화 설정 방식이 통합됐다.
 * 기존에 분산되어 있던 속성들이 {@code spring.sql.init.*} 아래로 통합되었다.</p>
 *
 * <h3>application.yml 예시</h3>
 * <pre>
 * spring:
 *   sql:
 *     init:
 *       schema-locations: classpath:schema.sql    # DDL 스크립트
 *       data-locations: classpath:data.sql         # DML 스크립트
 *       mode: always                               # always | embedded | never
 *       encoding: UTF-8
 * </pre>
 *
 * <h3>Deprecated in 2.5 (spring.datasource.*)</h3>
 * <ul>
 *   <li>{@code spring.datasource.schema} → {@code spring.sql.init.schema-locations}</li>
 *   <li>{@code spring.datasource.data} → {@code spring.sql.init.data-locations}</li>
 *   <li>{@code spring.datasource.initialization-mode} → {@code spring.sql.init.mode}</li>
 *   <li>{@code spring.datasource.sql-script-encoding} → {@code spring.sql.init.encoding}</li>
 * </ul>
 *
 * <h3>Info 엔드포인트 신기능 (2.5+)</h3>
 * <pre>
 * management:
 *   info:
 *     os:
 *       enabled: true    # OS 정보 노출 (/actuator/info)
 *     java:
 *       enabled: true    # JVM 버전 정보 노출
 * </pre>
 */
@Configuration
@EnableConfigurationProperties(SqlInitializationProperties.class)
public class SpringBoot25SqlInitConfig {
    // spring.sql.init.* 속성은 SqlInitializationAutoConfiguration이 자동으로 처리한다.
    // 별도 빈 설정 없이 application.yml 만으로 SQL 초기화를 제어할 수 있다.
}
