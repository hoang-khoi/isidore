package com.vietcatholicjp.isidore.domain.models.value_objects;

import java.util.Date;
import lombok.Data;

@Data
public class AuthClaim {

    private final String user;
    private final Date expiry;
}
