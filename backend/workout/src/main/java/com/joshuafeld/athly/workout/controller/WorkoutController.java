package com.joshuafeld.athly.workout.controller;

import com.joshuafeld.athly.workout.dto.WorkoutDto;
import com.joshuafeld.athly.workout.dto.WorkoutPostDto;
import com.joshuafeld.athly.workout.service.WorkoutService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * A workout controller.
 */
@RestController
@RequestMapping("/workouts")
public final class WorkoutController {

    private final WorkoutService service;

    /**
     * Creates an instance of an {@code WorkoutController} class.
     *
     * @param service the value for the {@code service} component
     */
    public WorkoutController(final WorkoutService service) {
        this.service = service;
    }

    /**
     * Creates a new workout.
     *
     * @param dto the data for the workout
     * @return the data of the workout
     */
    @PostMapping
    public WorkoutDto post(@RequestBody @Valid final WorkoutPostDto dto) {
        return service.post(dto);
    }

    /**
     * Returns the data of all workouts.
     *
     * @return a list of all workouts' data
     */
    @GetMapping
    public List<WorkoutDto> get() {
        return service.get();
    }

    /**
     * Returns the data of the workout with the given id.
     *
     * @param id the id of the workout
     * @return the data of the workout
     */
    @GetMapping("/{id}")
    public WorkoutDto get(@PathVariable final Long id) {
        return service.get(id);
    }

    /**
     * Deletes the data of the workout with the given id.
     *
     * @param id the id of the workout
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable final Long id) {
        service.delete(id);
    }
}
