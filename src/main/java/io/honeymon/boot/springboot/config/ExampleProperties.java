package io.honeymon.boot.springboot.config;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Data
@Validated
@ConfigurationProperties("example")
public class ExampleProperties {
	private boolean enabled;

	@NotNull
	private InetAddress remoteAddress;
	@Valid
	private final Security security = new Security();

	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@Getter
	@Setter
	public static class Security {
		@NotEmpty
		private String username;
		private String password;
		private List<String> roles = new ArrayList<>(Collections.singleton("USER"));

		@Builder
		public Security(String username, String password, List<String> roles) {
			this.username = username;
			this.password = password;
			this.roles = roles;
		}
	}
}
