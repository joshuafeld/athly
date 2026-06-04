package com.joshuafeld.athly.workout.controller;

import com.joshuafeld.athly.common.security.UserPrincipal;
import com.joshuafeld.athly.workout.command.CreateWorkoutCommand;
import com.joshuafeld.athly.workout.command.DeleteWorkoutCommand;
import com.joshuafeld.athly.workout.command.GetManyWorkoutsCommand;
import com.joshuafeld.athly.workout.command.GetWorkoutCommand;
import com.joshuafeld.athly.workout.command.ReplaceWorkoutCommand;
import com.joshuafeld.athly.workout.command.UpdateWorkoutCommand;
import com.joshuafeld.athly.workout.command.WorkoutResult;
import com.joshuafeld.athly.workout.dto.PatchWorkoutDto;
import com.joshuafeld.athly.workout.dto.PostWorkoutDto;
import com.joshuafeld.athly.workout.dto.PutWorkoutDto;
import com.joshuafeld.athly.workout.dto.WorkoutDto;
import com.joshuafeld.athly.workout.service.WorkoutService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
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
 * A workout controller.
 */
@Validated
@RestController
@RequestMapping("/workouts")
@AllArgsConstructor
public class WorkoutController {

    private final WorkoutService service;

    /**
     * Creates a new workout.
     *
     * @param dto the data for the workout
     * @param user the user principal
     * @return a created response with the location of the workout
     */
    @PostMapping
    public ResponseEntity<Void> post(
            @RequestBody @Valid final PostWorkoutDto dto,
            @AuthenticationPrincipal final UserPrincipal user
    ) {
        final var workout = service.create(new CreateWorkoutCommand(
                dto.name(), dto.notes(), user.id()
        ));
        return ResponseEntity
                .created(ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}").buildAndExpand(workout.id()).toUri())
                .build();
    }

    /**
     * Returns many workouts.
     *
     * @param user the user principal
     * @return an ok response with a list of many workouts
     */
    @GetMapping
    public ResponseEntity<List<WorkoutDto>> get(
            @AuthenticationPrincipal final UserPrincipal user
    ) {
        return ResponseEntity.ok(
                service.getMany(new GetManyWorkoutsCommand(user.id()))
                        .stream().map(this::fromResultToDto).toList()
        );
    }

    /**
     * Returns the workout with the given id.
     *
     * @param id the id of the workout
     * @param user the user principal
     * @return an ok response with the workout
     */
    @GetMapping("/{id}")
    public ResponseEntity<WorkoutDto> get(
            @PathVariable @Positive final Long id,
            @AuthenticationPrincipal final UserPrincipal user
    ) {
        return ResponseEntity.ok(
                fromResultToDto(service.get(new GetWorkoutCommand(id, user.id())))
        );
    }

    /**
     * Updates the workout with the given id.
     *
     * @param id the id of the workout
     * @param dto the data for the workout
     * @param principal the user principal
     * @return a no-content response with the location of the workout
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Void> patch(
            @PathVariable @Positive final Long id,
            @RequestBody @Valid final PatchWorkoutDto dto,
            @AuthenticationPrincipal final UserPrincipal principal
    ) {
        final var workout = service.update(new UpdateWorkoutCommand(
                id, dto.name(), dto.notes(), principal.id()
        ));
        return ResponseEntity.noContent()
                .location(ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}").buildAndExpand(workout.id()).toUri())
                .build();
    }

    /**
     * Replaces the workout with the given id.
     *
     * @param id the id of the workout
     * @param dto the data for the workout
     * @param principal the user principal
     * @return a no-content response with the location of the workout
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> put(
            @PathVariable @Positive final Long id,
            @RequestBody @Valid final PutWorkoutDto dto,
            @AuthenticationPrincipal final UserPrincipal principal
    ) {
        final var workout = service.replace(new ReplaceWorkoutCommand(
                id, dto.name(), dto.notes(), principal.id()
        ));
        return ResponseEntity.noContent()
                .location(ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}").buildAndExpand(workout.id()).toUri())
                .build();
    }

    /**
     * Deletes the workout with the given id.
     *
     * @param id the id of the workout
     * @param user the user principal
     * @return a no-content response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable @Positive final Long id,
            @AuthenticationPrincipal final UserPrincipal user
    ) {
        service.delete(new DeleteWorkoutCommand(id, user.id()));
        return ResponseEntity.noContent().build();
    }

    private WorkoutDto fromResultToDto(final WorkoutResult result) {
        return new WorkoutDto(result.id(), result.segmentIds(), result.name(), result.notes());
    }
}
