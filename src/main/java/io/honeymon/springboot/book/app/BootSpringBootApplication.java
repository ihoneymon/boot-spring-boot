package io.honeymon.springboot.book.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "io.honeymon.springboot.book.domain", "io.honeymon.springboot.book.service", "io.honeymon.springboot.book.web" })
@EnableJpaRepositories("io.honeymon.springboot.book.domain")
@EntityScan("io.honeymon.springboot.book.domain")
public class BootSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootSpringBootApplication.class, args);
	}
}
