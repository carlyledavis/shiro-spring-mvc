package com.carlyledavis.security;

import static java.lang.String.valueOf;

public class SecurityClaim {
    private String name;
    private String value;

    public SecurityClaim(String name, boolean value) {
        this.name = name;
        this.value = valueOf(value);
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
