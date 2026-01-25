package com.joshuafeld.athly.workout.controller;

import com.joshuafeld.athly.workout.dto.ExercisePatchDto;
import com.joshuafeld.athly.workout.dto.ExercisePostDto;
import com.joshuafeld.athly.workout.dto.ExerciseDto;
import com.joshuafeld.athly.workout.dto.ExercisePutDto;
import com.joshuafeld.athly.workout.service.ExerciseService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * An exercise controller.
 */
@RestController
@RequestMapping("/exercises")
public final class ExerciseController {

    private final ExerciseService service;

    /**
     * Creates an instance of an {@code ExerciseController} class.
     *
     * @param service the value for the {@code service} component
     */
    public ExerciseController(final ExerciseService service) {
        this.service = service;
    }

    /**
     * Creates a new exercise.
     *
     * @param dto the data for the exercise
     * @return the data of the exercise
     */
    @PostMapping
    public ExerciseDto post(@RequestBody @Valid final ExercisePostDto dto) {
        return service.post(dto);
    }

    /**
     * Returns the data of all exercises.
     *
     * @return a list of all exercises' data
     */
    @GetMapping
    public List<ExerciseDto> get() {
        return service.get();
    }

    /**
     * Returns the data of the exercise with the given id.
     *
     * @param id the id of the exercise
     * @return the data of the exercise
     */
    @GetMapping("/{id}")
    public ExerciseDto get(@PathVariable final Long id) {
        return service.get(id);
    }

    /**
     * Partially updates the data of the exercise with the given id.
     *
     * @param id the id of the exercise
     * @param dto the data for the exercise
     * @return the data of the exercise
     */
    @PatchMapping("/{id}")
    public ExerciseDto patch(@PathVariable final Long id,
                             @RequestBody @Valid final ExercisePatchDto dto) {
        return service.patch(id, dto);
    }

    /**
     * Updates the data of the exercise with the given id.
     *
     * @param id the id of the exercise
     * @param dto the data for the exercise
     * @return the data of the exercise
     */
    @PutMapping("/{id}")
    public ExerciseDto put(@PathVariable final Long id,
                           @RequestBody @Valid final ExercisePutDto dto) {
        return service.put(id, dto);
    }

    /**
     * Deletes the data of the exercise with the given id.
     *
     * @param id the id of the exercise
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable final Long id) {
        service.delete(id);
    }
}
