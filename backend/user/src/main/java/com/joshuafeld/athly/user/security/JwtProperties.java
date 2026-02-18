package com.joshuafeld.athly.user.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * A collection of JWT security configuration properties.
 *
 * @param ttl the value for the {@code ttl} component
 * @param publicKeyPem the value for the {@code publicKeyPem} component
 * @param privateKeyPem the value for the {@code privateKeyPem} component
 */
@ConfigurationProperties(prefix = "security.jwt")
public record JwtProperties(
        long ttl,
        String publicKeyPem,
        String privateKeyPem
) {

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

    public RSAPrivateKey privateKey() {
        try {
            return (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(
                    new PKCS8EncodedKeySpec(Base64.getDecoder().decode(
                            privateKeyPem
                                    .replaceAll("-----\\w+ PRIVATE KEY-----", "")
                                    .replaceAll("\\s", "")
                    ))
            );
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
