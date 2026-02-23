package com.joshuafeld.athly.workout.service;

import com.joshuafeld.athly.workout.dto.WorkoutDto;
import com.joshuafeld.athly.workout.mapper.WorkoutMapper;
import com.joshuafeld.athly.workout.model.Workout;
import com.joshuafeld.athly.workout.repository.WorkoutRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

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
     * @param creator the creator for the workout
     * @return the data of the workout
     */
    @Transactional
    public WorkoutDto post(final Long creator) {
        return mapper.toDto(repository.save(new Workout(creator,
                Collections.emptyList())));
    }

    /**
     * Returns the data of all workouts.
     *
     * @param creator the creator of the workouts
     * @return a list of all workouts' data
     */
    @Transactional(readOnly = true)
    public List<WorkoutDto> get(final Long creator) {
        return repository.findAllByCreator(creator).stream().map(mapper::toDto)
                .toList();
    }

    /**
     * Returns the data of the workout with the given id.
     *
     * @param id the id of the workout
     * @param creator the creator of the workout
     * @return the data of the workout
     */
    @Transactional(readOnly = true)
    public WorkoutDto get(final Long id, final Long creator) {
        return mapper.toDto(repository.requireByIdAndCreator(id, creator));
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

    public boolean isCreator(final Long id, final Jwt jwt) {
        return repository.findById(id).map(workout ->
                        workout.creator().equals(Long.valueOf(jwt.getSubject())))
                .orElse(false);
    }
}
