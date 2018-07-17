package io.honeymon.boot.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * 스프링 부트 애플리케이션이 시작되는 곳!
 * {@link SpringBootApplication}을 살펴보세요. 스프링 부투의 마법이 시작되는 곳입니다.
 *
 * @author honeymon
 *
 */
@ServletComponentScan
@SpringBootApplication
public class BootSpringBootApplication {
	public static void main(String[] args) {
		// SpringApplication.run(BootSpringBootApplication.class, args);

		SpringApplication app = new SpringApplication(BootSpringBootApplication.class);
		app.addListeners(new ApplicationPidFileWriter());
		//app.setWebEnvironment(false);	// 2.0 에서 Deprecated 됨
		app.setWebApplicationType(WebApplicationType.SERVLET);
		app.run(args);
	}
}
