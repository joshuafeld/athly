package com.joshuafeld.athly.workout.service;

import com.joshuafeld.athly.workout.dto.WorkoutDto;
import com.joshuafeld.athly.workout.dto.WorkoutPostDto;
import com.joshuafeld.athly.workout.model.Segment;
import com.joshuafeld.athly.workout.model.Workout;
import com.joshuafeld.athly.workout.repository.WorkoutRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * A workout service.
 */
@Service
public class WorkoutService {

    private final WorkoutRepository repository;

    /**
     * Creates an instance of a {@code WorkoutService} class.
     *
     * @param repository the value for the {@code repository} component
     */
    public WorkoutService(final WorkoutRepository repository) {
        this.repository = repository;
    }

    /**
     * Creates a new workout.
     *
     * @param dto the data for the workout
     * @return the data of the workout
     */
    @Transactional
    public WorkoutDto post(final WorkoutPostDto dto) {
        return toDto(repository.save(new Workout(dto.creator(),
                new ArrayList<>())));
    }

    /**
     * Returns the data of all workouts.
     *
     * @return a list of all workouts' data
     */
    @Transactional(readOnly = true)
    public List<WorkoutDto> get() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    /**
     * Returns the data of the workout with the given id.
     *
     * @param id the id of the workout
     * @return the data of the workout
     */
    @Transactional(readOnly = true)
    public WorkoutDto get(final Long id) {
        return toDto(repository.requireById(id));
    }

    /**
     * Deletes the data of the workout with the given id.
     *
     * @param id the id of the workout
     */
    @Transactional
    public void delete(final Long id) {
        repository.deleteById(id);
    }

    private WorkoutDto toDto(final Workout workout) {
        return new WorkoutDto(
                workout.id(),
                workout.creator(),
                workout.segments().stream().map(Segment::id).toList()
        );
    }
}
