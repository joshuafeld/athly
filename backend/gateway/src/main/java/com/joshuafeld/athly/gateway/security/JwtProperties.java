package com.joshuafeld.athly.gateway.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * A collection of JWT security configuration properties.
 *
 * @param publicKeyPem the value for the {@code publicKeyPem} component
 */
@ConfigurationProperties(prefix = "security.jwt")
public record JwtProperties(String publicKeyPem) {

    public RSAPublicKey publicKey() {
        try {
            return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(
                    new X509EncodedKeySpec(Base64.getDecoder().decode(
                            publicKeyPem
                                    .replaceAll("-----\\w+ PUBLIC KEY-----", "")
                                    .replaceAll("\\s", "")
                    ))
            );
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
