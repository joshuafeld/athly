package com.joshuafeld.athly.workout.service;

import com.joshuafeld.athly.workout.dto.WorkoutDto;
import com.joshuafeld.athly.workout.dto.WorkoutPostDto;
import com.joshuafeld.athly.workout.mapper.WorkoutMapper;
import com.joshuafeld.athly.workout.model.Segment;
import com.joshuafeld.athly.workout.model.Workout;
import com.joshuafeld.athly.workout.repository.WorkoutRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A workout service.
 */
@Service
public class WorkoutService {

    private final WorkoutRepository repository;

    private final WorkoutMapper mapper;

    /**
     * Creates an instance of a {@code WorkoutService} class.
     *
     * @param repository the value for the {@code repository} component
     * @param mapper the value for the {@code mapper} component
     */
    public WorkoutService(final WorkoutRepository repository,
                          final WorkoutMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Creates a new workout.
     *
     * @param dto the data for the workout
     * @return the data of the workout
     */
    @Transactional
    public WorkoutDto post(final WorkoutPostDto dto) {
        return mapper.toDto(repository.save(new Workout(dto.creator(),
                Collections.emptyList())));
    }

    /**
     * Returns the data of all workouts.
     *
     * @return a list of all workouts' data
     */
    @Transactional(readOnly = true)
    public List<WorkoutDto> get() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    /**
     * Returns the data of the workout with the given id.
     *
     * @param id the id of the workout
     * @return the data of the workout
     */
    @Transactional(readOnly = true)
    public WorkoutDto get(final Long id) {
        return mapper.toDto(repository.requireById(id));
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
}
