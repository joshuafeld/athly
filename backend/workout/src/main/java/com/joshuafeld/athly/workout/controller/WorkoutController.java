package com.joshuafeld.athly.workout.controller;

import com.joshuafeld.athly.common.security.UserPrincipal;
import com.joshuafeld.athly.workout.dto.WorkoutDto;
import com.joshuafeld.athly.workout.service.WorkoutService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

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
     * @param principal the user principal
     * @return the data of the workout
     */
    @PostMapping
    public WorkoutDto post(@AuthenticationPrincipal final UserPrincipal principal) {
        return service.post(principal.id());
    }

    /**
     * Returns the data of all workouts.
     *
     * @param principal the user principal
     * @return a list of all workouts' data
     */
    @GetMapping
    public List<WorkoutDto> get(@RequestParam(required = false) final Boolean all,
                                @RequestParam(required = false) final Long user,
                                @AuthenticationPrincipal final UserPrincipal principal) {
        Long principalId = principal.id();
        if (all != null && all) {
            return service.get(principalId);
        } else if (user != null) {
            return service.getByCreator(user, principalId);
        }
        return service.getByCreator(principalId, principalId);
    }

    /**
     * Returns the data of the workout with the given id.
     *
     * @param id the id of the workout
     * @param principal the user principal
     * @return the data of the workout
     */
    @GetMapping("/{id}")
    public WorkoutDto get(@PathVariable final Long id,
                          @AuthenticationPrincipal final UserPrincipal principal) {
        return service.get(id, principal.id());
    }

    /**
     * Deletes the data of the workout with the given id.
     *
     * @param id the id of the workout
     * @param principal the user principal
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable final Long id,
                       @AuthenticationPrincipal final UserPrincipal principal) {
        service.delete(id, principal.id());
    }
}
