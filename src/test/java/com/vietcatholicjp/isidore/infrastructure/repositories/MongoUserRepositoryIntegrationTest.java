package com.vietcatholicjp.isidore.infrastructure.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.mongodb.client.MongoClients;
import com.vietcatholicjp.isidore.domain.models.entities.User;
import com.vietcatholicjp.isidore.domain.models.value_objects.UserName;
import com.vietcatholicjp.isidore.domain.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoTemplate;

class MongoUserRepositoryIntegrationTest {

    private final MongoTemplate mongoTemplate = new MongoTemplate(
        MongoClients.create(),
        "it-database"
    );
    private final UserRepository underTest = new MongoUserRepository(mongoTemplate);

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        mongoTemplate.dropCollection(User.class);
    }

    @Test
    void save_UserNotExisted_SuccessfullyCreated() {
        User inputUser = new User("khoi@mail.com", new UserName("Joseph", "Khoi", "", "Hoang"));
        underTest.upsert(inputUser);
        User retrievedUser = mongoTemplate.findById(inputUser.getId(), User.class);

        assertEquals(inputUser, retrievedUser);
    }

    @Test
    void save_UserExisted_SuccessfullyUpdated() {
        User existedUser = new User("khoi@mail.com", new UserName("Joseph", "Khoi", "", "Hoang"));
        mongoTemplate.insert(existedUser);

        User updatedUser = new User("hoang@mail.com", new UserName("John", "Diep", "", "Tran"));
        updatedUser.setId(existedUser.getId());

        underTest.upsert(updatedUser);
        User retrievedUser = underTest.getById(existedUser.getId());

        assertEquals(updatedUser, retrievedUser);
    }

    @Test
    void save_EmailExisted_ThrowDuplicateKeyException() {
        User existedUser = new User("khoi@mail.com", new UserName("Joseph", "Khoi", "", "Hoang"));
        mongoTemplate.insert(existedUser);

        User newUser = new User("khoi@mail.com", new UserName("Teresa", "Diep", "", "Tran"));

        assertThrows(DuplicateKeyException.class, () -> underTest.upsert(newUser));
    }

    @Test
    void getById_UserExisted_SuccessfullyRetrieved() {
        User existedUser = new User("khoi@mail.com", new UserName("Joseph", "Khoi", "", "Hoang"));
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
