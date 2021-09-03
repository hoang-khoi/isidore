package com.vietcatholicjp.isidore.domain.services.crypt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BCryptService implements CryptService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public String hash(String rawPwd) {
        return bCryptPasswordEncoder.encode(rawPwd);
    }

    @Override
    public boolean matches(String rawPwd, String hashedPwd) {
        return bCryptPasswordEncoder.matches(rawPwd, hashedPwd);
    }
}
