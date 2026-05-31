package com.joshuafeld.athly.workout.repository;

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
}
