package com.vietcatholicjp.isidore.domain.services.crypt;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class BCryptServiceTest {
    private final CryptService underTest = new BCryptService();

    @Test
    void hash() {
        String hashed = underTest.hash("rawPassword");
        assertNotEquals("rawPassword", hashed);
    }

    @Test
    void name() {
        String hashed = "$2a$10$fl/v.qOhtsLB9dIo8fNcY.f2ZeMSIdF64SB5GM/Uc0.dgiwq55QVC";
        assertTrue(underTest.matches("rawPassword", "$2a$10$fl/v.qOhtsLB9dIo8fNcY.f2ZeMSIdF64SB5GM/Uc0.dgiwq55QVC"));
    }
}
