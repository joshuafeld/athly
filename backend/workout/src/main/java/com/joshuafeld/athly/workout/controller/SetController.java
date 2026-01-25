package com.joshuafeld.athly.workout.controller;

import com.joshuafeld.athly.workout.dto.SetDto;
import com.joshuafeld.athly.workout.dto.SetPatchDto;
import com.joshuafeld.athly.workout.dto.SetPostDto;
import com.joshuafeld.athly.workout.dto.SetPutDto;
import com.joshuafeld.athly.workout.service.SetService;
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
 * A set controller.
 */
@RestController
@RequestMapping("/workouts/{workout}/segments/{segment}/sets")
public class SetController {

    private final SetService service;

    /**
     * Creates an instance of a {@code SetController} class.
     *
     * @param service the value for the {@code service} component
     */
    public SetController(final SetService service) {
        this.service = service;
    }

    /**
     * Creates a new set.
     *
     * @param segment the id of the segment
     * @param dto the data for the set
     * @return the data of the set
     */
    @PostMapping
    public SetDto post(@PathVariable final Long segment,
                       @RequestBody @Valid final SetPostDto dto) {
        return service.post(segment, dto);
    }

    /**
     * Returns the data of all sets.
     *
     * @param segment the id of the segment
     * @return a list of all sets' data
     */
    @GetMapping
    public List<SetDto> get(@PathVariable final Long segment) {
        return service.get(segment);
    }

    /**
     * Returns the data of the segment with the given id.
     *
     * @param segment the id of the workout
     * @param id the id of the set
     * @return the data of the set
     */
    @GetMapping("/{id}")
    public SetDto get(@PathVariable final Long segment,
                      @PathVariable final Long id) {
        return service.get(segment, id);
    }

    /**
     * Partially updates the data of the set with the given id.
     *
     * @param id the id of the set
     * @param dto the data for the set
     * @return the data of the set
     */
    @PatchMapping("/{id}")
    public SetDto patch(@PathVariable final Long id,
                        @RequestBody @Valid final SetPatchDto dto) {
        return service.patch(id, dto);
    }

    /**
     * Updates the data of the set with the given id.
     *
     * @param id the id of the set
     * @param dto the data for the set
     * @return the data of the set
     */
    @PutMapping("/{id}")
    public SetDto put(@PathVariable final Long id,
                      @RequestBody @Valid final SetPutDto dto) {
        return service.put(id, dto);
    }

    /**
     * Deletes the data of the set with the given id.
     *
     * @param id the id of the set
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable final Long id) {
        service.delete(id);
    }
}
