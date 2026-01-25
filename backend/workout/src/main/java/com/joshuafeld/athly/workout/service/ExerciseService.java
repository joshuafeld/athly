package com.joshuafeld.athly.workout.service;

import com.joshuafeld.athly.workout.dto.ExerciseDto;
import com.joshuafeld.athly.workout.dto.ExercisePatchDto;
import com.joshuafeld.athly.workout.dto.ExercisePostDto;
import com.joshuafeld.athly.workout.dto.ExercisePutDto;
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

    /**
     * Creates an instance of an {@code ExerciseService} class.
     *
     * @param repository the value for the {@code repository} component
     */
    public ExerciseService(final ExerciseRepository repository) {
        this.repository = repository;
    }

    /**
     * Creates a new exercise.
     *
     * @param dto the data for the exercise
     * @return the data of the exercise
     */
    @Transactional
    public ExerciseDto post(final ExercisePostDto dto) {
        return toDto(repository.save(new Exercise(dto.name(), dto.equipment(),
                dto.muscle(), dto.creator())));
    }

    /**
     * Returns the data of all exercises.
     *
     * @return a list of all exercises' data
     */
    @Transactional(readOnly = true)
    public List<ExerciseDto> get() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    /**
     * Returns the data of the exercise with the given id.
     *
     * @param id the id of the exercise
     * @return the data of the exercise
     */
    @Transactional(readOnly = true)
    public ExerciseDto get(final Long id) {
        return toDto(repository.requireById(id));
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
        return toDto(repository.save(exercise));
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
        return toDto(repository.save(exercise));
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

    private ExerciseDto toDto(final Exercise exercise) {
        return new ExerciseDto(
                exercise.id(),
                exercise.name(),
                exercise.equipment(),
                exercise.muscle(),
                exercise.creator()
        );
    }
}
