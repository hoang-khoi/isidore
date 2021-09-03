package com.vietcatholicjp.isidore.application.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.vietcatholicjp.isidore.domain.models.entities.User;
import com.vietcatholicjp.isidore.domain.models.value_objects.UserName;
import com.vietcatholicjp.isidore.domain.repositories.UserRepository;
import com.vietcatholicjp.isidore.domain.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMutation implements GraphQLMutationResolver {

    private final UserRepository userRepository;
    private final UserService userService;

    public User user(String email, UserName name) {
        return userRepository.upsert(new User(email, name));
    }

    public User signup(String email, String pwd) {
        return userService.signup(email, pwd);
    }
}
