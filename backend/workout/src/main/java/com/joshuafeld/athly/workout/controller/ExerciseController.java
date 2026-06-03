package com.joshuafeld.athly.workout.controller;

import com.joshuafeld.athly.common.security.UserPrincipal;
import com.joshuafeld.athly.workout.command.CreateExerciseCommand;
import com.joshuafeld.athly.workout.command.DeleteExerciseCommand;
import com.joshuafeld.athly.workout.command.ExerciseResult;
import com.joshuafeld.athly.workout.command.GetAllExercisesCommand;
import com.joshuafeld.athly.workout.command.GetExerciseCommand;
import com.joshuafeld.athly.workout.command.ReplaceExerciseCommand;
import com.joshuafeld.athly.workout.command.UpdateExerciseCommand;
import com.joshuafeld.athly.workout.dto.ExerciseDto;
import com.joshuafeld.athly.workout.dto.PatchExerciseDto;
import com.joshuafeld.athly.workout.dto.PostExerciseDto;
import com.joshuafeld.athly.workout.dto.PutExerciseDto;
import com.joshuafeld.athly.workout.service.ExerciseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

/**
 * An exercise controller.
 */
@Validated
@RestController
@RequestMapping("/exercises")
@RequiredArgsConstructor
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
            @RequestBody @Valid final PostExerciseDto dto,
            @AuthenticationPrincipal final UserPrincipal user
    ) {
        final var exercise = service.create(new CreateExerciseCommand(
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
        return ResponseEntity.ok(service.getAll(new GetAllExercisesCommand(user.id()))
                .stream().map(this::fromResultToDto).toList());
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
            @PathVariable @Positive final Long id,
            @AuthenticationPrincipal final UserPrincipal user
    ) {
        return ResponseEntity.ok(fromResultToDto(service.get(new GetExerciseCommand(id, user.id()))));
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
            @PathVariable @Positive final Long id,
            @RequestBody @Valid final PatchExerciseDto dto,
            @AuthenticationPrincipal final UserPrincipal user
    ) {
        final var exercise = service.update(new UpdateExerciseCommand(
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
            @PathVariable @Positive final Long id,
            @RequestBody @Valid final PutExerciseDto dto,
            @AuthenticationPrincipal final UserPrincipal user
    ) {
        final var exercise = service.replace(new ReplaceExerciseCommand(
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
            @PathVariable @Positive final Long id,
            @AuthenticationPrincipal final UserPrincipal user
    ) {
        service.delete(new DeleteExerciseCommand(id, user.id()));
        return ResponseEntity.noContent().build();
    }

    private ExerciseDto fromResultToDto(final ExerciseResult result) {
        return new ExerciseDto(result.id(), result.name(), result.equipment(), result.muscle());
    }
}
