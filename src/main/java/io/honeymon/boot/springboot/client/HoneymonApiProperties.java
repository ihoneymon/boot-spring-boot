package io.honeymon.boot.springboot.client;

import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@NoArgsConstructor
@ConfigurationProperties("honeymon.api")
public class HoneymonApiProperties {
    private String rootUri;
    private String headerAuthorization; //header-authorization
    private Duration readTimeout;
    private Duration connectTimeout;

    public void setRootUri(String rootUri) {
        this.rootUri = rootUri;
    }

    public void setHeaderAuthorization(String headerAuthorization) {
        this.headerAuthorization = headerAuthorization;
    }

    public void setReadTimeout(Duration readTimeout) {
        this.readTimeout = readTimeout;
    }

    public void setConnectTimeout(Duration connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public String getRootUri() {
        return rootUri;
    }

    public String getHeaderAuthorization() {
        return headerAuthorization;
    }

    public Duration getReadTimeout() {
        return readTimeout;
    }

    public Duration getConnectTimeout() {
        return connectTimeout;
    }
}
