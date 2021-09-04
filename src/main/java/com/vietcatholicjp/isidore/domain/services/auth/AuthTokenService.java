package com.vietcatholicjp.isidore.domain.services.auth;

import com.vietcatholicjp.isidore.domain.models.value_objects.AuthClaim;

public interface AuthTokenService {

    String encode(AuthClaim claim);

    /**
     * Implementation note:
     * <ul>
     *     <li>Should throw an exception if the token is expired.</li>
     * </ul>
     */
    AuthClaim decode(String token);
}
