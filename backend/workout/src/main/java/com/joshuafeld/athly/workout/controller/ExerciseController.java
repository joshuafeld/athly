package com.joshuafeld.athly.workout.controller;

import com.joshuafeld.athly.common.security.UserPrincipal;
import com.joshuafeld.athly.workout.command.CreateExerciseCommand;
import com.joshuafeld.athly.workout.command.DeleteExerciseCommand;
import com.joshuafeld.athly.workout.command.GetExerciseCommand;
import com.joshuafeld.athly.workout.command.GetExercisesCommand;
import com.joshuafeld.athly.workout.command.ReplaceExerciseCommand;
import com.joshuafeld.athly.workout.command.UpdateExerciseCommand;
import com.joshuafeld.athly.workout.dto.ExercisePatchDto;
import com.joshuafeld.athly.workout.dto.ExercisePostDto;
import com.joshuafeld.athly.workout.dto.ExerciseDto;
import com.joshuafeld.athly.workout.dto.ExercisePutDto;
import com.joshuafeld.athly.workout.model.Exercise;
import com.joshuafeld.athly.workout.service.ExerciseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

/**
 * An exercise controller.
 */
@RestController
@RequestMapping("/exercises")
@AllArgsConstructor
public class ExerciseController {

    private final ExerciseService service;

    /**
     * Creates a new exercise.
     *
     * @param dto the data for the exercise
     * @param user the request user
     * @return the data of the exercise
     */
    @PostMapping
    public ResponseEntity<Void> post(
            @RequestBody @Valid final ExercisePostDto dto,
            @AuthenticationPrincipal final UserPrincipal user
    ) {
        final Exercise exercise = service.create(new CreateExerciseCommand(
                dto.name(), dto.equipment(), dto.muscle(), user.id()
        ));
        return ResponseEntity
                .created(ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}").buildAndExpand(exercise.id()).toUri())
                .build();
    }

    /**
     * Returns the data of all exercises.
     *
     * @param user the request user
     * @return a list of all exercises' data
     */
    @GetMapping
    public ResponseEntity<List<ExerciseDto>> get(
            @AuthenticationPrincipal final UserPrincipal user
    ) {
        return ResponseEntity.ok(service.getAll(new GetExercisesCommand(user.id()))
                .stream().map(this::toDto).toList());
    }

    /**
     * Returns the data of the exercise with the given id.
     *
     * @param id the id of the exercise
     * @param user the request user
     * @return the data of the exercise
     */
    @GetMapping("/{id}")
    public ResponseEntity<ExerciseDto> get(
            @PathVariable final Long id,
            @AuthenticationPrincipal final UserPrincipal user
    ) {
        return ResponseEntity.ok(
                toDto(service.getById(new GetExerciseCommand(id, user.id())))
        );
    }

    /**
     * Partially updates the data of the exercise with the given id.
     *
     * @param id the id of the exercise
     * @param dto the data for the exercise
     * @param user the request user
     * @return the data of the exercise
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Void> patch(
            @PathVariable final Long id,
            @RequestBody @Valid final ExercisePatchDto dto,
            @AuthenticationPrincipal final UserPrincipal user
    ) {
        final Exercise exercise = service.update(new UpdateExerciseCommand(
                id, dto.name(), dto.equipment(), dto.muscle(), user.id()
        ));
        return ResponseEntity.noContent()
                .location(ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}").buildAndExpand(exercise.id()).toUri())
                .build();
    }

    /**
     * Updates the data of the exercise with the given id.
     *
     * @param id the id of the exercise
     * @param dto the data for the exercise
     * @param user the request user
     * @return the data of the exercise
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> put(
            @PathVariable final Long id,
            @RequestBody @Valid final ExercisePutDto dto,
            @AuthenticationPrincipal final UserPrincipal user
    ) {
        final Exercise exercise = service.replace(new ReplaceExerciseCommand(
                id, dto.name(), dto.equipment(), dto.muscle(), user.id()
        ));
        return ResponseEntity.noContent()
                .location(ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}").buildAndExpand(exercise.id()).toUri())
                .build();
    }

    /**
     * Deletes the data of the exercise with the given id.
     *
     * @param id the id of the exercise
     * @param user the request user
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable final Long id,
            @AuthenticationPrincipal final UserPrincipal user
    ) {
        service.delete(new DeleteExerciseCommand(id, user.id()));
        return ResponseEntity.noContent().build();
    }

    private ExerciseDto toDto(final Exercise exercise) {
        return new ExerciseDto(
                exercise.id(),
                exercise.name(),
                exercise.equipment(),
                exercise.muscle()
        );
    }
}
