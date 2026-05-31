package com.joshuafeld.athly.workout.repository;

import com.joshuafeld.athly.workout.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * A workout repository.
 */
@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    /**
     * Retrieves all workouts by their owner.
     *
     * @param owner the id of the owner
     * @return a list of all workouts with the given owner
     */
    List<Workout> findAllByOwner(final Long owner);
}
