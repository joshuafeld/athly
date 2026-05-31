package com.joshuafeld.athly.workout.repository;

import com.joshuafeld.athly.workout.exception.ExerciseNotFoundException;
import com.joshuafeld.athly.workout.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * An exercise repository.
 */
@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    /**
     * Retrieves all exercises by their owner.
     *
     * @param owner the id of the owner
     * @return a list of all exercises
     */
    List<Exercise> findAllByOwner(final Long owner);

    /**
     * Retrieves an exercise by its id.
     *
     * @param id the id of the exercise
     * @return the exercise
     * @throws IllegalArgumentException if {@code id} is {@code null}
     * @throws ExerciseNotFoundException if the exercise does not exist
     */
    default Exercise requireById(final Long id) {
        return findById(id).orElseThrow(() -> new ExerciseNotFoundException(id));
    }
}
