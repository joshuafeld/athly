package com.joshuafeld.athly.workout.repository;

import com.joshuafeld.athly.workout.exception.ExerciseNotFoundException;
import com.joshuafeld.athly.workout.exception.SetNotFoundException;
import com.joshuafeld.athly.workout.model.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

/**
 * A set repository.
 */
@Repository
public interface SetRepository extends JpaRepository<Set, Long> {

    /**
     * Retrieves all sets by their segment id.
     *
     * @param segment the id of the segment
     * @return a list of all sets
     */
    default List<Set> findAll(final Long segment) {
        return findAll().stream().filter(set ->
                Objects.equals(set.segment().id(), segment)).toList();
    }

    /**
     * Retrieves a set by its id.
     *
     * @param id the id of the set
     * @return the set
     * @throws IllegalArgumentException if {@code id} is {@code null}
     * @throws SetNotFoundException if the set does not exist
     */
    default Set requireById(final Long id) {
        return findById(id).orElseThrow(() -> new SetNotFoundException(id));
    }
}
