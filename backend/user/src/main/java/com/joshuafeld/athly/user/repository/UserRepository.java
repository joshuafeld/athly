package com.joshuafeld.athly.user.repository;

import com.joshuafeld.athly.user.exception.UserNotFoundException;
import com.joshuafeld.athly.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A user repository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Retrieves a user by its id.
     *
     * @param id the id of the user
     * @return the user
     * @throws IllegalArgumentException if {@code id} is {@code null}
     * @throws UserNotFoundException if the user does not exist
     */
    default User requireById(Long id) {
        return findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
}
