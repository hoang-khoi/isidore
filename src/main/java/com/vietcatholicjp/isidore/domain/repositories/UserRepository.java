package com.vietcatholicjp.isidore.domain.repositories;

import com.vietcatholicjp.isidore.domain.models.entities.User;

public interface UserRepository {

    /**
     * Update an existing user, if it is not there, insert a new one (hence upsert, duh).
     * Implementation note:
     * <ul>
     *     <li>Make sure user.id *is set accordingly* after calling this method.</li>
     *     <li>Should throw an exception in the case of duplicated email.</li>
     * </ul>
     *
     * @param user A user instance to be persisted.
     */
    User upsert(User user);

    /**
     * @param id User's string ID.
     * @return The target user, null if not found.
     */
    User getById(String id);
}
