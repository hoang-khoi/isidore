package com.vietcatholicjp.isidore.domain.services.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.vietcatholicjp.isidore.domain.models.value_objects.AuthClaim;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

class JwtAuthTokenServiceTest {

    AuthTokenService underTest = new JwtAuthTokenService(
        Keys.hmacShaKeyFor("(G+KbPeShVmYq3t6w9z$C&E)H@McQfTj".getBytes(StandardCharsets.UTF_8))
    );

    @Test
    void decode_CorrectToken_SuccessfullyDecoded() {
        AuthClaim originalClaim = new AuthClaim("user", getExpiry());

        String token = underTest.encode(originalClaim);
        AuthClaim decodedClaim = underTest.decode(token);

        assertEquals(originalClaim.getUser(), decodedClaim.getUser());
        // HACK: Comparing time is a pain in the ass, this should do it.
        assertEquals(originalClaim.getExpiry().getTime() / 1000,
            decodedClaim.getExpiry().getTime() / 1000);
    }

    @Test
    void decode_ExpiredToken_ExceptionThrown() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjMwNzIyNzA4fQ.sX6CtcmPF0YrCrkguvdiIxLKsK2fyoyAoNpw7Jmml_w";
        assertThrows(ExpiredJwtException.class, () -> underTest.decode(token));
    }

    @Test
    void decode_WrongSignature_ExceptionThrown() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.d9E_UzIUu1db6M6FD5oaNbQEz74Iek5Rj-UUivTPf9c";
        assertThrows(SignatureException.class, () -> underTest.decode(token));
    }

    @Test
    void decode_BogusToken_ExceptionThrown() {
        assertThrows(MalformedJwtException.class, () -> underTest.decode("memes"));
    }

    @NotNull
    private Date getExpiry() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 1);
        return calendar.getTime();
    }
}
