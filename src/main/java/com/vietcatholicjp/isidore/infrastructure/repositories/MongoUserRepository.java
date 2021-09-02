package com.vietcatholicjp.isidore.infrastructure.repositories;

import com.vietcatholicjp.isidore.domain.models.entities.User;
import com.vietcatholicjp.isidore.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MongoUserRepository implements UserRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public User upsert(User user) {
        return mongoTemplate.save(user);
    }

    @Override
    public User getById(String id) {
        return mongoTemplate.findById(id, User.class);
    }
}
