package com.joshuafeld.athly.workout.repository;

import com.joshuafeld.athly.workout.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * An exercise repository.
 */
@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    /**
     * Deletes the exercise with the given id and owner id.
     *
     * @param id the id
     * @param ownerId the owner id
     * @return {@code 1} if an exercise was deleted, {@code 0} otherwise
     */
    @Modifying
    @Query("""
        delete from Exercise e
        where e.id = :id
          and e.ownerId = :ownerId
    """)
    int deleteByIdAndOwnerId(final Long id, final Long ownerId);

    /**
     * Retrieves all exercises by their owner id.
     *
     * @param ownerId the owner id
     * @return a list of all exercises with the given owner id
     */
    List<Exercise> findAllByOwnerId(final Long ownerId);

    /**
     * Retrieves an exercise by its id and owner id.
     *
     * @param id the id
     * @param ownerId the owner id
     * @return the exercise with the given id and owner or {@code Optional#empty()} if none found
     */
    Optional<Exercise> findByIdAndOwnerId(final Long id, final Long ownerId);
}
