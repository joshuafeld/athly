package com.joshuafeld.athly.workout.service;

import com.joshuafeld.athly.workout.dto.*;
import com.joshuafeld.athly.workout.mapper.ExerciseMapper;
import com.joshuafeld.athly.workout.model.Exercise;
import com.joshuafeld.athly.workout.repository.ExerciseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * An exercise service.
 */
@Service
public class ExerciseService {

    private final ExerciseRepository repository;

    private final ExerciseMapper mapper;

    /**
     * Creates an instance of an {@code ExerciseService} class.
     *
     * @param repository the value for the {@code repository} component
     * @param mapper the value for the {@code mapper} component
     */
    public ExerciseService(final ExerciseRepository repository,
                           final ExerciseMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Creates a new exercise.
     *
     * @param dto the data for the exercise
     * @return the data of the exercise
     */
    @Transactional
    public ExerciseDto post(final ExercisePostDto dto) {
        return mapper.toDto(repository.save(new Exercise(dto.name(),
                dto.equipment(), dto.muscle(), dto.creator())));
    }

    /**
     * Returns the data of all exercises.
     *
     * @return a list of all exercises' data
     */
    @Transactional(readOnly = true)
    public List<ExerciseDto> get() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    /**
     * Returns the data of the exercise with the given id.
     *
     * @param id the id of the exercise
     * @return the data of the exercise
     */
    @Transactional(readOnly = true)
    public ExerciseDto get(final Long id) {
        return mapper.toDto(repository.requireById(id));
    }

    /**
     * Partially updates the data of the exercise with the given id.
     *
     * @param id the id of the exercise
     * @param dto the data for the exercise
     * @return the data of the exercise
     */
    @Transactional
    public ExerciseDto patch(final Long id, final ExercisePatchDto dto) {
        Exercise exercise = repository.requireById(id);
        Optional.ofNullable(dto.name()).ifPresent(exercise::name);
        Optional.ofNullable(dto.equipment()).ifPresent(exercise::equipment);
        Optional.ofNullable(dto.muscle()).ifPresent(exercise::muscle);
        Optional.ofNullable(dto.creator()).ifPresent(exercise::creator);
        return mapper.toDto(repository.save(exercise));
    }

    /**
     * Updates the data of the exercise with the given id.
     *
     * @param id the id of the exercise
     * @param dto the data for the exercise
     * @return the data of the exercise
     */
    @Transactional
    public ExerciseDto put(final Long id, final ExercisePutDto dto) {
        Exercise exercise = repository.requireById(id);
        exercise.name(dto.name());
        exercise.equipment(dto.equipment());
        exercise.muscle(dto.muscle());
        exercise.creator(dto.creator());
        return mapper.toDto(repository.save(exercise));
    }

    /**
     * Deletes the data of the exercise with the given id.
     *
     * @param id the id of the exercise
     */
    @Transactional
    public void delete(final Long id) {
        repository.deleteById(id);
    }
}
