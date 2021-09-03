package com.vietcatholicjp.isidore.infrastructure.repositories;

import com.vietcatholicjp.isidore.domain.models.entities.User;
import com.vietcatholicjp.isidore.domain.repositories.UserRepository;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.stereotype.Repository;

@Repository
public class MongoUserRepository implements UserRepository {

    private final MongoTemplate mongoTemplate;

    public MongoUserRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
        this.mongoTemplate.indexOps(User.class)
            .ensureIndex(new Index("email", Direction.ASC).unique());
    }

    @Override
    public User upsert(User user) {
        return mongoTemplate.save(user);
    }

    @Override
    public User getById(String id) {
        return mongoTemplate.findById(id, User.class);
    }
}
