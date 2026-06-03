package com.joshuafeld.athly.workout.service;

import com.joshuafeld.athly.workout.command.CreateExerciseCommand;
import com.joshuafeld.athly.workout.command.DeleteExerciseCommand;
import com.joshuafeld.athly.workout.command.ExerciseIdResult;
import com.joshuafeld.athly.workout.command.ExerciseResult;
import com.joshuafeld.athly.workout.command.GetExerciseCommand;
import com.joshuafeld.athly.workout.command.GetAllExercisesCommand;
import com.joshuafeld.athly.workout.command.ReplaceExerciseCommand;
import com.joshuafeld.athly.workout.command.UpdateExerciseCommand;
import com.joshuafeld.athly.workout.exception.ExerciseNotFoundException;
import com.joshuafeld.athly.workout.model.Exercise;
import com.joshuafeld.athly.workout.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * An exercise service.
 */
@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository repository;

    /**
     * Creates a new exercise.
     *
     * @param command the create command
     * @return an exercise id result
     */
    @Transactional
    public ExerciseIdResult create(final CreateExerciseCommand command) {
        return new ExerciseIdResult(repository.save(new Exercise(
                command.name(),
                command.equipment(),
                command.muscle(),
                command.owner()
        )).id());
    }

    /**
     * Returns all exercises.
     *
     * @param command the get-all command
     * @return a list of all exercise results
     */
    @Transactional(readOnly = true)
    public List<ExerciseResult> getAll(final GetAllExercisesCommand command) {
        return repository.findAllByOwner(command.owner())
                .stream().map(this::fromEntityToResult).toList();
    }

    /**
     * Returns an exercise.
     *
     * @param command the get command
     * @return an exercise result
     */
    @Transactional(readOnly = true)
    public ExerciseResult get(final GetExerciseCommand command) {
        return fromEntityToResult(requireByIdAndOwner(command.id(), command.owner()));
    }

    /**
     * Updates an exercise.
     *
     * @param command the update command
     * @return an exercise id result
     */
    @Transactional
    public ExerciseIdResult update(final UpdateExerciseCommand command) {
        final var exercise = requireByIdAndOwner(command.id(), command.owner());
        Optional.ofNullable(command.name()).ifPresent(exercise::name);
        Optional.ofNullable(command.equipment()).ifPresent(exercise::equipment);
        Optional.ofNullable(command.muscle()).ifPresent(exercise::muscle);
        return fromEntityToIdResult(repository.save(exercise));
    }

    /**
     * Replaces an exercise.
     *
     * @param command the replace command
     * @return an exercise id result
     */
    @Transactional
    public ExerciseIdResult replace(final ReplaceExerciseCommand command) {
        final var exercise = requireByIdAndOwner(command.id(), command.owner());
        exercise.name(command.name());
        exercise.equipment(command.equipment());
        exercise.muscle(command.muscle());
        return fromEntityToIdResult(repository.save(exercise));
    }

    /**
     * Deletes an exercise.
     *
     * @param command the delete command
     */
    @Transactional
    public void delete(final DeleteExerciseCommand command) {
        final var id = command.id();
        if (repository.deleteByIdAndOwner(id, command.owner()) == 0) {
            throw new ExerciseNotFoundException(id);
        }
    }

    private Exercise requireByIdAndOwner(final Long id, final Long owner) {
        return repository.findByIdAndOwner(id, owner)
                .orElseThrow(() -> new ExerciseNotFoundException(id));
    }

    private ExerciseResult fromEntityToResult(final Exercise entity) {
        return new ExerciseResult(
                entity.id(),
                entity.name(),
                entity.equipment(),
                entity.muscle()
        );
    }

    private ExerciseIdResult fromEntityToIdResult(final Exercise entity) {
        return new ExerciseIdResult(entity.id());
    }
}
