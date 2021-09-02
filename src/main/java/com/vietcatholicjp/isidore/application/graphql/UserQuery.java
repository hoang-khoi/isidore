package com.vietcatholicjp.isidore.application.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.vietcatholicjp.isidore.domain.models.entities.User;
import com.vietcatholicjp.isidore.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserQuery implements GraphQLQueryResolver {

    private final UserRepository userRepository;

    public User user(String id) {
        return userRepository.getById(id);
    }
}
