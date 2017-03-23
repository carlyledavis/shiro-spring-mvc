package com.carlyledavis.security;

public class AuthenticationPolicy {
    private long tokenDuration;

    public AuthenticationPolicy(long tokenDuration) {
        this.tokenDuration = tokenDuration;
    }


    public long getTokenDuration() {
        return tokenDuration;
    }
}
