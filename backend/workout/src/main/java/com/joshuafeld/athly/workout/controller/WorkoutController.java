package com.joshuafeld.athly.workout.controller;

import com.joshuafeld.athly.workout.dto.WorkoutDto;
import com.joshuafeld.athly.workout.service.WorkoutService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * @param jwt the web token
     * @return the data of the workout
     */
    @PostMapping
    public WorkoutDto post(@AuthenticationPrincipal final Jwt jwt) {
        return service.post(Long.valueOf(jwt.getSubject()));
    }

    /**
     * Returns the data of all workouts.
     *
     * @param jwt the web token
     * @return a list of all workouts' data
     */
    @GetMapping
    public List<WorkoutDto> get(@AuthenticationPrincipal final Jwt jwt) {
        return service.get(Long.valueOf(jwt.getSubject()));
    }

    /**
     * Returns the data of the workout with the given id.
     *
     * @param id the id of the workout
     * @param jwt the web token
     * @return the data of the workout
     */
    @GetMapping("/{id}")
    public WorkoutDto get(@PathVariable final Long id,
                          @AuthenticationPrincipal final Jwt jwt) {
        return service.get(id, Long.valueOf(jwt.getSubject()));
    }

    /**
     * Deletes the data of the workout with the given id.
     *
     * @param id the id of the workout
     * @param jwt the web token
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("@workoutService.isCreator(#id, #jwt)")
    public void delete(@PathVariable final Long id,
                       @AuthenticationPrincipal final Jwt jwt) {
        service.delete(id);
    }
}
