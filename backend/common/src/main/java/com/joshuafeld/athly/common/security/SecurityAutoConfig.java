package com.joshuafeld.athly.common.security;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * A security autoconfiguration.
 */
@AutoConfiguration
@EnableMethodSecurity
@EnableConfigurationProperties(JwtProperties.class)
public class SecurityAutoConfig {

    /**
     * Configures the default security filter chain.
     *
     * @param http the HTTP security builder
     * @param jwtDecoder the JWT decoder
     * @return the configured security filter chain
     */
    @Bean
    public SecurityFilterChain defaultFilterChain(final HttpSecurity http,
                                           final JwtDecoder jwtDecoder) {
        return http
                .securityMatcher("/**")
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry -> registry
                        .requestMatchers("/actuator/**").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(
                        jwt -> jwt.decoder(jwtDecoder)
                ))
                .build();
    }

    /**
     * Configures the JWT decoder.
     *
     * @param properties the JWT configuration properties
     * @return the configured JWT decoder
     */
    @Bean
    public JwtDecoder jwtDecoder(final JwtProperties properties) {
        return NimbusJwtDecoder.withPublicKey(properties.publicKey()).build();
    }
}
