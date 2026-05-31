package com.joshuafeld.athly.workout.service;

import com.joshuafeld.athly.workout.command.*;
import com.joshuafeld.athly.workout.exception.ExerciseAccessDeniedException;
import com.joshuafeld.athly.workout.model.Exercise;
import com.joshuafeld.athly.workout.repository.ExerciseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * An exercise service.
 */
@Service
@AllArgsConstructor
public class ExerciseService {

    private final ExerciseRepository repository;

    /**
     * Creates a new exercise.
     *
     * @param command the create command
     * @return the exercise
     */
    @Transactional
    public Exercise create(final CreateExerciseCommand command) {
        return repository.save(new Exercise(
                command.name(),
                command.equipment(),
                command.muscle(),
                command.owner()
        ));
    }

    /**
     * Returns all exercises.
     *
     * @param command the get-all command
     * @return a list of all exercises
     */
    @Transactional(readOnly = true)
    public List<Exercise> getAll(final GetExercisesCommand command) {
        return repository.findAllByOwner(command.owner());
    }

    /**
     * Returns the exercise with the given id.
     *
     * @param command the get-by-id command
     * @return the exercise
     */
    @Transactional(readOnly = true)
    public Exercise getById(final GetExerciseCommand command) {
        return requireOwner(command.id(), command.owner());
    }

    /**
     * Updates the exercise with the given id.
     *
     * @param command the update command
     * @return the exercise
     */
    @Transactional
    public Exercise update(final UpdateExerciseCommand command) {
        final Exercise exercise = requireOwner(command.id(), command.owner());
        Optional.ofNullable(command.name()).ifPresent(exercise::name);
        Optional.ofNullable(command.equipment()).ifPresent(exercise::equipment);
        Optional.ofNullable(command.muscle()).ifPresent(exercise::muscle);
        return repository.save(exercise);
    }

    /**
     * Replaces the exercise with the given id.
     *
     * @param command the replace command
     * @return the exercise
     */
    @Transactional
    public Exercise replace(final ReplaceExerciseCommand command) {
        final Exercise exercise = requireOwner(command.id(), command.owner());
        exercise.name(command.name());
        exercise.equipment(command.equipment());
        exercise.muscle(command.muscle());
        return repository.save(exercise);
    }

    /**
     * Deletes the exercise with the given id.
     *
     * @param command the delete command
     */
    @Transactional
    public void delete(final DeleteExerciseCommand command) {
        repository.delete(requireOwner(command.id(), command.owner()));
    }

    private Exercise requireOwner(final Long id, final Long owner) {
        final Exercise exercise = repository.requireById(id);
        if (!exercise.owner().equals(owner)) {
            throw new ExerciseAccessDeniedException(id);
        }
        return exercise;
    }
}
