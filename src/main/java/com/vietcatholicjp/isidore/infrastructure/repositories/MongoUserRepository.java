package com.vietcatholicjp.isidore.infrastructure.repositories;

import com.vietcatholicjp.isidore.domain.entities.User;
import com.vietcatholicjp.isidore.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;

@RequiredArgsConstructor
public class MongoUserRepository implements UserRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public void upsert(User user) {
        mongoTemplate.save(user);
    }

    @Override
    public User getById(String id) {
        return mongoTemplate.findById(id, User.class);
    }
}
