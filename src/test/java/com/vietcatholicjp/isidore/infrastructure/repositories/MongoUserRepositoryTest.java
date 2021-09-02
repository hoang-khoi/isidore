package com.vietcatholicjp.isidore.infrastructure.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.mongodb.client.MongoClients;
import com.vietcatholicjp.isidore.domain.entities.User;
import com.vietcatholicjp.isidore.domain.entities.User.Name;
import com.vietcatholicjp.isidore.domain.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.MongoTemplate;

class MongoUserRepositoryTest {

    private MongoTemplate mongoTemplate = new MongoTemplate(MongoClients.create(), "database");
    private UserRepository underTest = new MongoUserRepository(mongoTemplate);

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        mongoTemplate.dropCollection(User.class);
    }

    @Test
    void save_UserNotExisted_SuccessfullyCreated() {
        User inputUser = new User("khoi@mail.com", new User.Name("Joseph", "Khoi", "", "Hoang"));
        underTest.upsert(inputUser);
        User retrievedUser = mongoTemplate.findById(inputUser.getId(), User.class);

        assertEquals(inputUser, retrievedUser);
    }

    @Test
    void save_UserExisted_SuccessfullyUpdated() {
        User existedUser = new User("khoi@mail.com", new User.Name("Joseph", "Khoi", "", "Hoang"));
        mongoTemplate.insert(existedUser);

        User updatedUser = new User("hoang@mail.com", new Name("John", "Diep", "Hong Thi", "Tran"));
        updatedUser.setId(existedUser.getId());

        underTest.upsert(updatedUser);
        User retrievedUser = underTest.getById(existedUser.getId());

        assertEquals(updatedUser, retrievedUser);
    }

    @Test
    void getById_UserExisted_SuccessfullyRetrieved() {
        User existedUser = new User("khoi@mail.com", new User.Name("Joseph", "Khoi", "", "Hoang"));
        mongoTemplate.insert(existedUser);
        User retrievedUser = underTest.getById(existedUser.getId());

        assertEquals(existedUser, retrievedUser);
    }

    @Test
    void getById_UserNotExisted_ReturnsNull() {
        User retrievedUser = underTest.getById("nonexistence");
        assertNull(retrievedUser);
    }
}
