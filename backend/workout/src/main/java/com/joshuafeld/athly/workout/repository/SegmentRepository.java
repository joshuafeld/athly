package com.joshuafeld.athly.workout.repository;

import com.joshuafeld.athly.workout.exception.SegmentNotFoundException;
import com.joshuafeld.athly.workout.model.Segment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

/**
 * A segment repository.
 */
@Repository
public interface SegmentRepository extends JpaRepository<Segment, Long> {

    /**
     * Retrieves all segments by their workout id.
     *
     * @param workout the id of the workout
     * @return a list of all segments
     */
    default List<Segment> findAll(final Long workout) {
        return findAll().stream().filter(segment ->
                Objects.equals(segment.workout().id(), workout)).toList();
    }

    /**
     * Retrieves a segment by its id.
     *
     * @param id the id of the segment
     * @return the segment
     * @throws IllegalArgumentException if {@code id} is {@code null}
     * @throws SegmentNotFoundException if the segment does not exist
     */
    default Segment requireById(final Long id) {
        return findById(id).orElseThrow(() -> new SegmentNotFoundException(id));
    }
}
