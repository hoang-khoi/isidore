package com.vietcatholicjp.isidore.domain.services.crypt;

public interface CryptService {
    String hash(String rawPwd);
    boolean matches(String rawPwd, String hashedPwd);
}
