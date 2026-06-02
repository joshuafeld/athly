package com.joshuafeld.athly.workout.service;

import com.joshuafeld.athly.workout.command.CreateExerciseCommand;
import com.joshuafeld.athly.workout.command.DeleteExerciseCommand;
import com.joshuafeld.athly.workout.command.GetExerciseCommand;
import com.joshuafeld.athly.workout.command.GetExercisesCommand;
import com.joshuafeld.athly.workout.command.ReplaceExerciseCommand;
import com.joshuafeld.athly.workout.command.UpdateExerciseCommand;
import com.joshuafeld.athly.workout.exception.ExerciseNotFoundException;
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
     * @param command the get command
     * @return a list of all exercises
     */
    @Transactional(readOnly = true)
    public List<Exercise> getAll(final GetExercisesCommand command) {
        return repository.findAllByOwner(command.owner());
    }

    /**
     * Returns the exercise with the given id.
     *
     * @param command the get command
     * @return the exercise
     */
    @Transactional(readOnly = true)
    public Exercise getById(final GetExerciseCommand command) {
        return requireByIdAndOwner(command.id(), command.owner());
    }

    /**
     * Updates the exercise with the given id.
     *
     * @param command the update command
     * @return the exercise
     */
    @Transactional
    public Exercise update(final UpdateExerciseCommand command) {
        final Exercise exercise = requireByIdAndOwner(command.id(), command.owner());
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
        final Exercise exercise = requireByIdAndOwner(command.id(), command.owner());
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
        final Long id = command.id();
        if (repository.deleteByIdAndOwner(id, command.owner()) == 0) {
            throw new ExerciseNotFoundException(id);
        }
    }

    private Exercise requireByIdAndOwner(final Long id, final Long owner) {
        return repository.findByIdAndOwner(id, owner)
                .orElseThrow(() -> new ExerciseNotFoundException(id));
    }
}
