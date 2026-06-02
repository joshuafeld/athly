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
     * Deletes the workout with the given id and owner.
     *
     * @param id the id
     * @param owner the owner
     * @return {@code 1} if a workout was deleted, {@code 0} otherwise
     */
    @Modifying
    @Query("""
        delete from Workout w
        where w.id = :id
          and w.owner = :owner
    """)
    int deleteByIdAndOwner(final Long id, final Long owner);

    /**
     * Retrieves all workouts by their owner.
     *
     * @param owner the owner
     * @return a list of all workouts with the given owner
     */
    List<Workout> findAllByOwner(final Long owner);

    /**
     * Retrieves a workout by its id and owner.
     *
     * @param id the id
     * @param owner the owner
     * @return the workout with the given id and owner or {@code Optional#empty()} if none found
     */
    Optional<Workout> findByIdAndOwner(final Long id, final Long owner);
}
