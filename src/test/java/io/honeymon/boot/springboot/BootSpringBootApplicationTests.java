package io.honeymon.boot.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Spring Boot 2.4.0 부터 junit-vintage-engine 이 제거되었다.
 * Spring Boot 2.4.0 으로 업그레이드 하려면 JUnit5 로 이관을 마쳐야 한다.
 */
@SpringBootTest
public class BootSpringBootApplicationTests {

	@Test
	public void contextLoads() {
	}
}
