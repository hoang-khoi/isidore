package com.vietcatholicjp.isidore.domain.services.auth;

import com.vietcatholicjp.isidore.domain.config.JwtConfig;
import com.vietcatholicjp.isidore.domain.models.value_objects.AuthClaim;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthTokenService implements AuthTokenService {

    private final JwtConfig jwtConfig;

    @Override
    public String encode(AuthClaim claim) {
        return Jwts.builder()
            .signWith(jwtConfig.getKey())
            .setSubject(claim.getUser())
            .setExpiration(claim.getExpiry())
            .compact();
    }

    @Override
    public AuthClaim decode(String token) {
        var claim = Jwts.parserBuilder()
            .setSigningKey(jwtConfig.getKey())
            .build()
            .parseClaimsJws(token);

        return new AuthClaim(claim.getBody().getSubject(), claim.getBody().getExpiration());
    }
}
