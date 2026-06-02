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
     * Deletes the exercise with the given id and owner.
     *
     * @param id the id
     * @param owner the owner
     * @return {@code 1} if an exercise was deleted, {@code 0} otherwise
     */
    @Modifying
    @Query("""
        delete from Exercise e
        where e.id = :id
          and e.owner = :owner
    """)
    int deleteByIdAndOwner(final Long id, final Long owner);

    /**
     * Retrieves all exercises by their owner.
     *
     * @param owner the owner
     * @return a list of all exercises with the given owner
     */
    List<Exercise> findAllByOwner(final Long owner);

    /**
     * Retrieves an exercise by its id and owner.
     *
     * @param id the id
     * @param owner the owner
     * @return the exercise with the given id and owner or {@code Optional#empty()} if none found
     */
    Optional<Exercise> findByIdAndOwner(final Long id, final Long owner);
}
