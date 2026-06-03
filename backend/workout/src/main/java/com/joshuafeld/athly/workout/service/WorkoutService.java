package com.joshuafeld.athly.workout.service;

import com.joshuafeld.athly.workout.command.CreateWorkoutCommand;
import com.joshuafeld.athly.workout.command.DeleteWorkoutCommand;
import com.joshuafeld.athly.workout.command.GetWorkoutCommand;
import com.joshuafeld.athly.workout.command.GetAllWorkoutsCommand;
import com.joshuafeld.athly.workout.command.ReplaceWorkoutCommand;
import com.joshuafeld.athly.workout.command.UpdateWorkoutCommand;
import com.joshuafeld.athly.workout.command.WorkoutIdResult;
import com.joshuafeld.athly.workout.command.WorkoutResult;
import com.joshuafeld.athly.workout.exception.WorkoutNotFoundException;
import com.joshuafeld.athly.workout.model.Segment;
import com.joshuafeld.athly.workout.model.Workout;
import com.joshuafeld.athly.workout.repository.WorkoutRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * A workout service.
 */
@Service
@AllArgsConstructor
public class WorkoutService {

    private final WorkoutRepository repository;

    /**
     * Creates a new workout.
     *
     * @param command the create command
     * @return a workout id result
     */
    @Transactional
    public WorkoutIdResult create(final CreateWorkoutCommand command) {
        return new WorkoutIdResult(repository.save(new Workout(
                command.owner(),
                Collections.emptyList(),
                command.name(),
                command.notes()
        )).id());
    }

    /**
     * Returns all workouts.
     *
     * @param command the get-all command
     * @return a list of all workout results
     */
    @Transactional(readOnly = true)
    public List<WorkoutResult> getAll(final GetAllWorkoutsCommand command) {
        return repository.findAllByOwner(command.owner())
                .stream().map(this::fromEntityToResult).toList();
    }

    /**
     * Returns a workout.
     *
     * @param command the get command
     * @return a workout result
     */
    @Transactional(readOnly = true)
    public WorkoutResult get(final GetWorkoutCommand command) {
        return fromEntityToResult(requireByIdAndOwner(command.id(), command.owner()));
    }

    /**
     * Updates a workout.
     *
     * @param command the update command
     * @return a workout id result
     */
    @Transactional
    public WorkoutIdResult update(final UpdateWorkoutCommand command) {
        final var workout = requireByIdAndOwner(command.id(), command.owner());
        Optional.ofNullable(command.name()).ifPresent(workout::name);
        Optional.ofNullable(command.notes()).ifPresent(workout::notes);
        return fromEntityToIdResult(repository.save(workout));
    }

    /**
     * Replaces a workout.
     *
     * @param command the replace command
     * @return a workout id result
     */
    @Transactional
    public WorkoutIdResult replace(final ReplaceWorkoutCommand command) {
        final var workout = requireByIdAndOwner(command.id(), command.owner());
        workout.name(command.name());
        workout.notes(command.notes());
        return fromEntityToIdResult(repository.save(workout));
    }

    /**
     * Deletes a workout.
     *
     * @param command the delete command
     */
    @Transactional
    public void delete(final DeleteWorkoutCommand command) {
        final var id = command.id();
        if (repository.deleteByIdAndOwner(id, command.owner()) == 1) {
            throw new WorkoutNotFoundException(id);
        }
    }

    private Workout requireByIdAndOwner(final Long id, final Long owner) {
        return repository.findByIdAndOwner(id, owner)
                .orElseThrow(() -> new WorkoutNotFoundException(id));
    }

    private WorkoutResult fromEntityToResult(final Workout entity) {
        return new WorkoutResult(
                entity.id(),
                entity.segments().stream().map(Segment::id).toList(),
                entity.name(),
                entity.notes()
        );
    }

    private WorkoutIdResult fromEntityToIdResult(final Workout entity) {
        return new WorkoutIdResult(entity.id());
    }
}
