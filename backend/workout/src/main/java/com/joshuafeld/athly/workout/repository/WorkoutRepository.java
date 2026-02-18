package com.joshuafeld.athly.workout.repository;

import com.joshuafeld.athly.workout.exception.WorkoutNotFoundException;
import com.joshuafeld.athly.workout.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * A workout repository.
 */
@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    /**
     * Retrieves all workouts by their creator.
     *
     * @param creator the creator of the workout
     * @return the workouts with the given creator
     * @throws IllegalArgumentException if {@code creator} is {@code null}
     */
    List<Workout> findAllByCreator(final Long creator);

    /**
     * Retrieves a workout by its id and creator.
     *
     * @param id must not be {@code null}
     * @param creator must not be {@code null}
     * @return the workout with the given id and creator
     * @throws IllegalArgumentException if {@code id} or {@code creator} is
     *                                  {@code null}
     */
    Optional<Workout> findByIdAndCreator(final Long id, final Long creator);

    /**
     * Retrieves a workout by its id.
     *
     * @param id must not be {@code null}
     * @return the workout with the given id
     * @throws IllegalArgumentException if {@code id} is {@code null}
     * @throws WorkoutNotFoundException if the workout does not exist
     */
    default Workout requireById(final Long id) {
        return findById(id).orElseThrow(() -> new WorkoutNotFoundException(id));
    }

    /**
     * Retrieves a workout by its id and creator.
     *
     * @param id must not be {@code null}
     * @param creator must not be {@code null}
     * @return the workout with the given id and creator
     * @throws IllegalArgumentException if {@code id} or {@code creator} is
     *                                  {@code null}
     * @throws WorkoutNotFoundException if the workout does not exist
     */
    default Workout requireByIdAndCreator(final Long id, final Long creator) {
        return findByIdAndCreator(id, creator).orElseThrow(
                () -> new WorkoutNotFoundException(id, creator)
        );
    }
}
