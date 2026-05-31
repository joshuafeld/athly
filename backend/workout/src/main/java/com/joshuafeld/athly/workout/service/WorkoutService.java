package com.joshuafeld.athly.workout.service;

import com.joshuafeld.athly.workout.dto.*;
import com.joshuafeld.athly.workout.mapper.WorkoutMapper;
import com.joshuafeld.athly.workout.model.Workout;
import com.joshuafeld.athly.workout.repository.WorkoutRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final WorkoutMapper mapper;

    /**
     * Creates a new workout.
     *
     * @param dto the data for the workout
     * @param user the request user
     * @return the data of the workout
     */
    @Transactional
    public WorkoutDto post(final WorkoutPostDto dto, final Long user) {
        return mapper.toDto(repository.save(new Workout(user,
                Collections.emptyList(), dto.name(), dto.notes())));
    }

    /**
     * Returns the data of all workouts.
     *
     * @param user the request user
     * @return a list of all workouts' data
     */
    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<WorkoutDto> get(final Long user) {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    /**
     * Returns the data of all workouts for the given creator.
     *
     * @param creator the creator of the workouts
     * @param user the request user
     * @return a list of all workouts' data for the given creator
     */
    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ROLE_ADMIN') or #creator.equals(#user)")
    public List<WorkoutDto> getByCreator(final Long creator, final Long user) {
        return repository.findAllByCreator(creator).stream().map(mapper::toDto)
                .toList();
    }

    /**
     * Returns the data of the workout with the given id.
     *
     * @param id the id of the workout
     * @param user the request user
     * @return the data of the workout
     */
    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ROLE_ADMIN') or @workoutService.isCreator(#id, #user)")
    public WorkoutDto get(final Long id, final Long user) {
        return mapper.toDto(repository.requireById(id));
    }

    /**
     * Partially updates the data of the workout with the given id.
     *
     * @param id the id of the workout
     * @param dto the data for the workout
     * @param user the request user
     * @return the data of the workout
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN') or @workoutService.isCreator(#id, #user)")
    public WorkoutDto patch(
            final Long id,
            final WorkoutPatchDto dto,
            final Long user
    ) {
        Workout workout = repository.requireById(id);
        Optional.ofNullable(dto.name()).ifPresent(workout::name);
        Optional.ofNullable(dto.notes()).ifPresent(workout::notes);
        return mapper.toDto(repository.save(workout));
    }

    /**
     * Updates the data of the workout with the given id.
     *
     * @param id the id of the workout
     * @param dto the data for the workout
     * @param user the request user
     * @return the data of the workout
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN') or @workoutService.isCreator(#id, #user)")
    public WorkoutDto put(
            final Long id,
            final WorkoutPutDto dto,
            final Long user
    ) {
        Workout workout = repository.requireById(id);
        workout.name(dto.name());
        workout.notes(dto.notes());
        return mapper.toDto(repository.save(workout));
    }

    /**
     * Deletes the data of the workout with the given id.
     *
     * @param id the id of the workout
     * @param user the request user
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN') or @workoutService.isCreator(#id, #user)")
    public void delete(final Long id, final Long user) {
        repository.deleteById(id);
    }

    public boolean isCreator(final Long id, final Long user) {
        return repository.findById(id).map(workout ->
                        workout.creator().equals(user))
                .orElse(false);
    }
}
