package com.joshuafeld.athly.workout.controller;

import com.joshuafeld.athly.common.security.UserPrincipal;
import com.joshuafeld.athly.workout.command.CreateWorkoutCommand;
import com.joshuafeld.athly.workout.command.DeleteWorkoutCommand;
import com.joshuafeld.athly.workout.command.GetWorkoutCommand;
import com.joshuafeld.athly.workout.command.GetWorkoutsCommand;
import com.joshuafeld.athly.workout.command.ReplaceWorkoutCommand;
import com.joshuafeld.athly.workout.command.UpdateWorkoutCommand;
import com.joshuafeld.athly.workout.dto.WorkoutDto;
import com.joshuafeld.athly.workout.dto.WorkoutPatchDto;
import com.joshuafeld.athly.workout.dto.WorkoutPostDto;
import com.joshuafeld.athly.workout.dto.WorkoutPutDto;
import com.joshuafeld.athly.workout.model.Segment;
import com.joshuafeld.athly.workout.model.Workout;
import com.joshuafeld.athly.workout.service.WorkoutService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

/**
 * A workout controller.
 */
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
     * @return a created response with the location of the created workout
     */
    @PostMapping
    public ResponseEntity<Void> post(
            @RequestBody @Valid final WorkoutPostDto dto,
            @AuthenticationPrincipal final UserPrincipal user
    ) {
        final Workout workout = service.create(new CreateWorkoutCommand(
                dto.name(), dto.notes(), user.id()
        ));
        return ResponseEntity
                .created(ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}").buildAndExpand(workout.id()).toUri())
                .build();
    }

    /**
     * Returns all workouts.
     *
     * @param user the user principal
     * @return an ok response with a list of all workouts' data
     */
    @GetMapping
    public ResponseEntity<List<WorkoutDto>> get(
            @AuthenticationPrincipal final UserPrincipal user
    ) {
        return ResponseEntity.ok(
                service.getAll(new GetWorkoutsCommand(user.id()))
                        .stream().map(this::toDto).toList()
        );
    }

    /**
     * Returns the workout with the given id.
     *
     * @param id the id of the workout
     * @param user the user principal
     * @return an ok response with the workout data
     */
    @GetMapping("/{id}")
    public ResponseEntity<WorkoutDto> get(
            @PathVariable final Long id,
            @AuthenticationPrincipal final UserPrincipal user
    ) {
        return ResponseEntity.ok(
                toDto(service.getById(new GetWorkoutCommand(id, user.id())))
        );
    }

    /**
     * Partially updates the data of the workout with the given id.
     *
     * @param id the id of the workout
     * @param dto the data for the workout
     * @param principal the user principal
     * @return a no-content response with the location of the updated workout
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Void> patch(
            @PathVariable final Long id,
            @RequestBody @Valid final WorkoutPatchDto dto,
            @AuthenticationPrincipal final UserPrincipal principal
    ) {
        final Workout workout = service.update(new UpdateWorkoutCommand(
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
     * @return a no-content response with the location of the replaced workout
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> put(
            @PathVariable final Long id,
            @RequestBody @Valid final WorkoutPutDto dto,
            @AuthenticationPrincipal final UserPrincipal principal
    ) {
        final Workout workout = service.replace(new ReplaceWorkoutCommand(
                id, dto.name(), dto.notes(), principal.id()
        ));
        return ResponseEntity.noContent()
                .location(ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}").buildAndExpand(workout.id()).toUri())
                .build();
    }

    /**
     * Deletes the data of the workout with the given id.
     *
     * @param id the id of the workout
     * @param user the user principal
     * @return a no-content response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable final Long id,
            @AuthenticationPrincipal final UserPrincipal user
    ) {
        service.delete(new DeleteWorkoutCommand(id, user.id()));
        return ResponseEntity.noContent().build();
    }

    private WorkoutDto toDto(final Workout workout) {
        return new WorkoutDto(
                workout.id(),
                workout.segments().stream().map(Segment::id).toList(),
                workout.name(),
                workout.notes()
        );
    }
}
