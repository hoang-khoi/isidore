package com.vietcatholicjp.isidore.domain.models.entities;

import com.vietcatholicjp.isidore.domain.models.value_objects.UserName;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class User {

    @Id
    private String id;
    private final String email;
    private final UserName name;
    private String hashedPwd;
}
