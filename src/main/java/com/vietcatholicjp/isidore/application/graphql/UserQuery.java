package com.vietcatholicjp.isidore.application.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.vietcatholicjp.isidore.domain.models.entities.User;
import com.vietcatholicjp.isidore.domain.repositories.UserRepository;
import com.vietcatholicjp.isidore.domain.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserQuery implements GraphQLQueryResolver {

    private final UserRepository userRepository;
    private final UserService userService;

    public User user(String id) {
        return userRepository.getById(id);
    }

    public String login(String email, String pwd) {
        return userService.login(email, pwd);
    }
}
