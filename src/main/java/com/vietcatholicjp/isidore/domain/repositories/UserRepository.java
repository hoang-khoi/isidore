package com.vietcatholicjp.isidore.domain.repositories;

import com.vietcatholicjp.isidore.domain.entities.User;

public interface UserRepository {

    /**
     * Update an existing user, if it is not there, insert a new one (hence upsert, duh).
     * Implementation note: make sure user.id *is set accordingly* after calling this method.
     *
     * @param user A user instance to be persisted.
     */
    void upsert(User user);

    /**
     * @param id User's string ID.
     * @return The target user, null if not found.
     */
    User getById(String id);
}
