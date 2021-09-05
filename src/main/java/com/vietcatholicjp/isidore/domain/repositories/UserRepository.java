package com.vietcatholicjp.isidore.domain.repositories;

import com.vietcatholicjp.isidore.domain.models.entities.User;

public interface UserRepository {

    /**
     * Implementation note:
     * <ul>
     *     <li>Make sure user.id *is set accordingly* after calling this method.</li>
     *     <li>Should throw an exception in the case of duplicated email.</li>
     * </ul>
     *
     * @param user A user instance to be persisted.
     */
    User insert(User user);

    /**
     * @param id User's string ID.
     * @return The target user, null if not found.
     */
    User findById(String id);

    /**
     * @param email User's email
     * @return Target user, null if not found.
     */
    User findByEmail(String email);
}
