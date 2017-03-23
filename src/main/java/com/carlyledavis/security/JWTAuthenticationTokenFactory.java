package com.carlyledavis.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;

import java.io.UnsupportedEncodingException;
import java.time.Clock;
import java.util.List;

public class JWTAuthenticationTokenFactory {


    private final Clock clock;
    private final AuthenticationPolicy authenticationPolicy;
    private String tokenExpiry;

    public JWTAuthenticationTokenFactory(Clock clock, AuthenticationPolicy authenticationPolicy) {
        this.clock = clock;
        this.authenticationPolicy = authenticationPolicy;
    }

    public String createToken(String username, List<SecurityClaim> claims) throws UnsupportedEncodingException {
        JWTCreator.Builder builder = JWT.create();
        claims.forEach(claim -> builder.withClaim(claim.getName(), claim.getValue()));
        builder.withClaim("iss", getTokenExpiry());
        builder.withClaim( "usr", username );

        builder.withSubject("com.carlyledavis");
        return builder.sign(Algorithm.HMAC256("really-secret-key"));
    }

    public String getTokenExpiry() {
        return String.valueOf(clock.millis() + authenticationPolicy.getTokenDuration());
    }
}
