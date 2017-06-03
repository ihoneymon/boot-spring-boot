package io.honeymon.springboot.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationPidFileWriter;

@SpringBootApplication
public class BootSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(BootSpringBootApplication.class);
		app.addListeners(new ApplicationPidFileWriter());
		app.run(args);
	}
	
}
