package com.joshuafeld.athly.gateway.security;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * A security configuration.
 */
@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class SecurityConfig {

    /**
     * Configures the security filter chain.
     *
     * @param http the HTTP security builder
     * @return the configured security filter chain
     */
    @Bean
    SecurityFilterChain filterChain(final HttpSecurity http) {
        return http
                .securityMatcher("/user-management/auth/**")
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry -> registry
                        .requestMatchers(HttpMethod.POST, "/user-management/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/user-management/auth/register").permitAll()
                )
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
