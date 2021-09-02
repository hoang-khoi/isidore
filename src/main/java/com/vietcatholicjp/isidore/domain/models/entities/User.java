package com.vietcatholicjp.isidore.domain.models.entities;

import com.vietcatholicjp.isidore.domain.models.value_objects.UserName;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class User {

    @Indexed(unique = true)
    private final String email;
    private final UserName name;
    private String id;
}
