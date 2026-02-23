package com.joshuafeld.athly.user.repository;

import com.joshuafeld.athly.user.exception.UserNotFoundException;
import com.joshuafeld.athly.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * A user repository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Retrieves a user by its username.
     *
     * @param username must not be {@code null}
     * @return the user with the given username or {@code Optional#empty()}
     *         if none found
     * @throws IllegalArgumentException if {@code username} is {@code null}
     */
    Optional<User> findByUsername(final String username);

    /**
     * Retrieves a user by its id.
     *
     * @param id must not be {@code null}
     * @return the user with the given id
     * @throws IllegalArgumentException if {@code id} is {@code null}
     * @throws UserNotFoundException if the user does not exist
     */
    default User requireById(final Long id) {
        return findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
}
