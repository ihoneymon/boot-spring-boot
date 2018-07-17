package io.honeymon.boot.springboot.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("springboot").password("password").roles("USER");
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .requestMatcher(EndpointRequest.toAnyEndpoint())
                .authorizeRequests()
                .antMatchers("/greeting").anonymous();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return null;
    }
}
