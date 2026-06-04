package com.joshuafeld.athly.workout.repository;

import com.joshuafeld.athly.workout.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * A workout repository.
 */
@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    /**
     * Deletes the workout with the given id and owner id.
     *
     * @param id the id
     * @param ownerId the owner id
     * @return {@code 1} if a workout was deleted, {@code 0} otherwise
     */
    @Modifying
    @Query("""
        delete from Workout w
        where w.id = :id
          and w.ownerId = :ownerId
    """)
    int deleteByIdAndOwnerId(final Long id, final Long ownerId);

    /**
     * Retrieves all workouts by their owner id.
     *
     * @param ownerId the owner id
     * @return a list of all workouts with the given owner id
     */
    List<Workout> findAllByOwnerId(final Long ownerId);

    /**
     * Retrieves a workout by its id and owner id.
     *
     * @param id the id
     * @param ownerId the owner id
     * @return the workout with the given id and owner id or {@code Optional#empty()} if none found
     */
    Optional<Workout> findByIdAndOwnerId(final Long id, final Long ownerId);
}
