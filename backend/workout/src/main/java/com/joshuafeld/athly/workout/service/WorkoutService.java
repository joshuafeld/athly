package com.joshuafeld.athly.workout.service;

import com.joshuafeld.athly.workout.command.CreateWorkoutCommand;
import com.joshuafeld.athly.workout.command.DeleteWorkoutCommand;
import com.joshuafeld.athly.workout.command.GetWorkoutCommand;
import com.joshuafeld.athly.workout.command.GetWorkoutsCommand;
import com.joshuafeld.athly.workout.command.ReplaceWorkoutCommand;
import com.joshuafeld.athly.workout.command.UpdateWorkoutCommand;
import com.joshuafeld.athly.workout.exception.WorkoutNotFoundException;
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
     * @return the workout
     */
    @Transactional
    public Workout create(final CreateWorkoutCommand command) {
        return repository.save(new Workout(
                command.owner(),
                Collections.emptyList(),
                command.name(),
                command.notes()
        ));
    }

    /**
     * Returns all workouts.
     *
     * @param command the get command
     * @return a list of all workouts
     */
    @Transactional(readOnly = true)
    public List<Workout> getAll(final GetWorkoutsCommand command) {
        return repository.findAllByOwner(command.owner());
    }

    /**
     * Returns the workout with the given id.
     *
     * @param command the get command
     * @return the data of the workout
     */
    @Transactional(readOnly = true)
    public Workout getById(final GetWorkoutCommand command) {
        return requireByIdAndOwner(command.id(), command.owner());
    }

    /**
     * Updates the workout with the given id.
     *
     * @param command the update command
     * @return the workout
     */
    @Transactional
    public Workout update(final UpdateWorkoutCommand command) {
        Workout workout = requireByIdAndOwner(command.id(), command.owner());
        Optional.ofNullable(command.name()).ifPresent(workout::name);
        Optional.ofNullable(command.notes()).ifPresent(workout::notes);
        return repository.save(workout);
    }

    /**
     * Replace the workout with the given id.
     *
     * @param command the replace command
     * @return the workout
     */
    @Transactional
    public Workout replace(final ReplaceWorkoutCommand command) {
        final Workout workout = requireByIdAndOwner(command.id(), command.owner());
        workout.name(command.name());
        workout.notes(command.notes());
        return repository.save(workout);
    }

    /**
     * Deletes the workout with the given id.
     *
     * @param command the delete command
     */
    @Transactional
    public void delete(final DeleteWorkoutCommand command) {
        final Long id = command.id();
        if (repository.deleteByIdAndOwner(id, command.owner()) == 1) {
            throw new WorkoutNotFoundException(id);
        }
    }

    private Workout requireByIdAndOwner(final Long id, final Long owner) {
        return repository.findByIdAndOwner(id, owner)
                .orElseThrow(() -> new WorkoutNotFoundException(id));
    }
}
