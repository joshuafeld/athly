package com.joshuafeld.athly.workout.controller;

import com.joshuafeld.athly.workout.dto.SegmentDto;
import com.joshuafeld.athly.workout.dto.SegmentPostDto;
import com.joshuafeld.athly.workout.dto.SegmentPatchDto;
import com.joshuafeld.athly.workout.dto.SegmentPutDto;
import com.joshuafeld.athly.workout.service.SegmentService;
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
 * A segment controller.
 */
@RestController
@RequestMapping("/workouts/{workout}/segments")
public class SegmentController {

    private final SegmentService service;

    /**
     * Creates an instance of a {@code SegmentController} class.
     *
     * @param service the value for the {@code service} component
     */
    public SegmentController(final SegmentService service) {
        this.service = service;
    }

    /**
     * Creates a new segment.
     *
     * @param workout the id of the workout
     * @param dto the data for the segment
     * @return the data of the segment
     */
    @PostMapping
    public SegmentDto post(@PathVariable final Long workout,
                           @RequestBody @Valid final SegmentPostDto dto) {
        return service.post(workout, dto);
    }

    /**
     * Returns the data of all segments.
     *
     * @param workout the id of the workout
     * @return a list of all segments' data
     */
    @GetMapping
    public List<SegmentDto> get(@PathVariable final Long workout) {
        return service.get(workout);
    }

    /**
     * Returns the data of the segment with the given id.
     *
     * @param workout the id of the workout
     * @param id the id of the segment
     * @return the data of the segment
     */
    @GetMapping("/{id}")
    public SegmentDto get(@PathVariable final Long workout,
                          @PathVariable final Long id) {
        return service.get(workout, id);
    }

    /**
     * Partially updates the data of the segment with the given id.
     *
     * @param workout the id of the workout
     * @param id the id of the segment
     * @param dto the data for the segment
     * @return the data of the segment
     */
    @PatchMapping("/{id}")
    public SegmentDto patch(@PathVariable final Long workout,
                            @PathVariable final Long id,
                            @RequestBody @Valid final SegmentPatchDto dto) {
        return service.patch(id, dto);
    }

    /**
     * Updates the data of the segment with the given id.
     *
     * @param workout the id of the workout
     * @param id the id of the segment
     * @param dto the data for the segment
     * @return the data of the segment
     */
    @PutMapping("/{id}")
    public SegmentDto put(@PathVariable final Long workout,
                          @PathVariable final Long id,
                          @RequestBody @Valid final SegmentPutDto dto) {
        return service.put(id, dto);
    }

    /**
     * Deletes the data of the segment with the given id.
     *
     * @param workout the id of the workout
     * @param id the id of the segment
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable final Long workout,
                       @PathVariable final Long id) {
        service.delete(id);
    }
}
