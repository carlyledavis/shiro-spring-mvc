package com.carlyledavis.security;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JWTRealmTest {

    private JWTAuthenticationToken jwtAuthenticationToken;
    private JWTRealm realm;
    private JWTValidator validator;

    @Before
    public void setUp() throws Exception {
        jwtAuthenticationToken = mock(JWTAuthenticationToken.class);
        validator = mock(JWTValidator.class);
        realm = new JWTRealm(validator);
    }

    @Test( expected = SecurityException.class)
    public void shouldReturnAnErrorIfJWTIsNotValid(){
        when(validator.validate(any(JWTAuthenticationToken.class))).thenThrow(SecurityException.class);
        realm.getAuthenticationInfo(jwtAuthenticationToken);
    }

    @Test
    public void shouldReturnThatItSupportsJWTAuthenticationToken(){
        assertThat( realm.supports( mock(JWTAuthenticationToken.class))).isTrue();
    }

    public void shouldReturnValidAuthenticaitonInfo() {


    }
}