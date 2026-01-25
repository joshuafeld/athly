package com.joshuafeld.athly.workout.service;

import com.joshuafeld.athly.workout.dto.SegmentDto;
import com.joshuafeld.athly.workout.dto.SegmentPostDto;
import com.joshuafeld.athly.workout.dto.SegmentPatchDto;
import com.joshuafeld.athly.workout.dto.SegmentPutDto;
import com.joshuafeld.athly.workout.model.Segment;
import com.joshuafeld.athly.workout.model.Set;
import com.joshuafeld.athly.workout.repository.ExerciseRepository;
import com.joshuafeld.athly.workout.repository.SegmentRepository;
import com.joshuafeld.athly.workout.repository.WorkoutRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A segment service.
 */
@Service
public class SegmentService {

    private final ExerciseRepository exerciseRepository;
    private final WorkoutRepository workoutRepository;
    private final SegmentRepository repository;

    /**
     * Creates an instance of an {@code SegmentService} class.
     *
     * @param repository the value for the {@code repository} component
     * @param exerciseRepository the value for the {@code exerciseRepository}
     *                           component
     * @param workoutRepository the value for the {@code workoutRepository}
     *                          component
     */
    public SegmentService(final SegmentRepository repository,
                          final ExerciseRepository exerciseRepository,
                          final WorkoutRepository workoutRepository) {
        this.repository = repository;
        this.exerciseRepository = exerciseRepository;
        this.workoutRepository = workoutRepository;
    }

    /**
     * Creates a new segment.
     *
     * @param workout the id of the workout
     * @param dto the data for the segment
     * @return the data of the segment
     */
    @Transactional
    public SegmentDto post(final Long workout, final SegmentPostDto dto) {
        return toDto(repository.save(new Segment(
                workoutRepository.requireById(workout),
                exerciseRepository.requireById(dto.exercise()),
                new ArrayList<>(),
                dto.rest()
        )));
    }

    /**
     * Returns the data of all segments.
     *
     * @param workout the id of the workout
     * @return a list of all segments' data
     */
    @Transactional(readOnly = true)
    public List<SegmentDto> get(final Long workout) {
        return repository.findAll(workout).stream().map(this::toDto).toList();
    }

    /**
     * Returns the data of the segment with the given id.
     *
     * @param workout the id of the workout
     * @param id the id of the segment
     * @return the data of the segment
     */
    @Transactional(readOnly = true)
    public SegmentDto get(final Long workout, final Long id) {
        return toDto(repository.requireById(id));
    }

    /**
     * Partially updates the data of the segment with the given id.
     *
     * @param id the id of the segment
     * @param dto the data for the segment
     * @return the data of the segment
     */
    @Transactional
    public SegmentDto patch(final Long id, final SegmentPatchDto dto) {
        Segment segment = repository.requireById(id);
        Optional.ofNullable(dto.exercise()).ifPresent(exercise ->
                segment.exercise(exerciseRepository.requireById(exercise)));
        Optional.ofNullable(dto.rest()).ifPresent(segment::rest);
        return toDto(repository.save(segment));
    }

    /**
     * Updates the data of the segment with the given id.
     *
     * @param id the id of the segment
     * @param dto the data for the segment
     * @return the data of the segment
     */
    @Transactional
    public SegmentDto put(final Long id, final SegmentPutDto dto) {
        Segment segment = repository.requireById(id);
        segment.exercise(exerciseRepository.requireById(dto.exercise()));
        segment.rest(dto.rest());
        return toDto(repository.save(segment));
    }

    /**
     * Deletes the data of the segment with the given id.
     *
     * @param id the id of the segment
     */
    @Transactional
    public void delete(final Long id) {
        repository.deleteById(id);
    }

    private SegmentDto toDto(final Segment segment) {
        return new SegmentDto(
                segment.id(),
                segment.workout().id(),
                segment.exercise().id(),
                segment.sets().stream().map(Set::id).toList(),
                segment.rest()
        );
    }
}
