package com.joshuafeld.athly.workout.repository;

import com.joshuafeld.athly.workout.exception.WorkoutNotFoundException;
import com.joshuafeld.athly.workout.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A workout repository.
 */
@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    /**
     * Retrieves a workout by its id.
     *
     * @param id the id of the workout
     * @return the workout
     * @throws IllegalArgumentException if {@code id} is {@code null}
     * @throws WorkoutNotFoundException if the workout does not exist
     */
    default Workout requireById(final Long id) {
        return findById(id).orElseThrow(() -> new WorkoutNotFoundException(id));
    }
}
