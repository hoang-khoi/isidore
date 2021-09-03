package com.vietcatholicjp.isidore.domain.services.user;

import com.vietcatholicjp.isidore.domain.models.entities.User;

public interface UserService {
    User signup(String email, String pwd);
    String login(String email, String pwd);
}
