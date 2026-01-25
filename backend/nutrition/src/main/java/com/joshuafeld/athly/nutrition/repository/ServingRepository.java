package com.joshuafeld.athly.nutrition.repository;

import com.joshuafeld.athly.nutrition.exception.ServingNotFoundException;
import com.joshuafeld.athly.nutrition.model.Serving;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Objects;

/**
 * A serving repository.
 */
public interface ServingRepository extends JpaRepository<Serving, Long> {

    /**
     * Retrieves all servings by their food id.
     *
     * @param food the id of the food
     * @return a list of all servings
     */
    default List<Serving> findAll(final Long food) {
        return findAll().stream().filter(serving ->
                Objects.equals(serving.food().id(), food)).toList();
    }

    /**
     * Retrieves a serving by its id.
     *
     * @param id the id of the serving
     * @return the serving
     * @throws IllegalArgumentException if {@code id} is {@code null}
     * @throws ServingNotFoundException if the serving does not exist
     */
    default Serving requireById(final Long id) {
        return findById(id).orElseThrow(() -> new ServingNotFoundException(id));
    }
}
