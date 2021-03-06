package com.vietcatholicjp.isidore.application.graphql.user;

import com.vietcatholicjp.isidore.domain.models.entities.User;
import com.vietcatholicjp.isidore.domain.repositories.UserRepository;
import com.vietcatholicjp.isidore.domain.services.user.UserService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserQuery implements GraphQLQueryResolver {

    private final UserRepository userRepository;
    private final UserService userService;

    public User user(String id) {
        return userRepository.findById(id);
    }

    public String login(String email, String pwd) {
        return userService.login(email, pwd);
    }
}
