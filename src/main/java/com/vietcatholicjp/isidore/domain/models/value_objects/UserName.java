package com.vietcatholicjp.isidore.domain.models.value_objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserName {

    private String baptismalName;
    private String firstName;
    private String middleName;
    private String lastName;
}
