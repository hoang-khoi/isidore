package com.vietcatholicjp.isidore.domain.entities;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class User {

    @Data
    public static class Name {

        private final String baptismalName;
        private final String firstName;
        private final String middleName;
        private final String lastName;
    }

    private String id;
    private final String email;
    private final Name name;
}
