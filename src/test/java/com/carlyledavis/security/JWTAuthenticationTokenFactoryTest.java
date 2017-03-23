package com.carlyledavis.security;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.time.Clock;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JWTAuthenticationTokenFactoryTest {

    @Test
    public void shouldGenerateValidBase64TokenWithToStringAndNoClaims() throws UnsupportedEncodingException {
        Clock clock = mock(Clock.class);
        when(clock.millis()).thenReturn(0L);

        JWTAuthenticationTokenFactory factory = new JWTAuthenticationTokenFactory(clock, new AuthenticationPolicy( 100L ));
        List<SecurityClaim> claims = new ArrayList<>();
        String token = factory.createToken( "username", claims);

        String expectedString = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjb20uY2FybHlsZWRhdmlzIiwidXNyIjoidXNlcm5hbWUiLCJpc3MiOiIxMDAifQ.HOpl7YhgdwIzVCbOpwGfUp2ukrPrcKKMBTKaQ7WUcFs";
        assertThat( token ).isEqualTo(expectedString);
    }

    @Test
    public void shouldGenerateValidTokenUsingTheListOfClaimsProvided() throws UnsupportedEncodingException {
        Clock clock = mock(Clock.class);
        when(clock.millis()).thenReturn(0L);

        JWTAuthenticationTokenFactory factory = new JWTAuthenticationTokenFactory(clock, new AuthenticationPolicy( 100L ));
        List<SecurityClaim> claims = new ArrayList<>();
        claims.add( new SecurityClaim( "make-payment", true ));
        String token = factory.createToken( "username", claims);

        String expectedString = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjb20uY2FybHlsZWRhdmlzIiwidXNyIjoidXNlcm5hbWUiLCJpc3MiOiIxMDAiLCJtYWtlLXBheW1lbnQiOiJ0cnVlIn0.QK81X6SKiSBah3-No_wJq4VyFFwnC9I4DWqwJsGO3D4";
        assertThat( token ).isEqualTo(expectedString);

    }

}