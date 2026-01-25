package com.joshuafeld.athly.nutrition.controller;

import com.joshuafeld.athly.nutrition.dto.ServingDto;
import com.joshuafeld.athly.nutrition.dto.ServingPatchDto;
import com.joshuafeld.athly.nutrition.dto.ServingPostDto;
import com.joshuafeld.athly.nutrition.dto.ServingPutDto;
import com.joshuafeld.athly.nutrition.service.ServingService;
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
 * A serving controller.
 */
@RestController
@RequestMapping("/foods/{food}/servings")
public final class ServingController {

    private final ServingService service;

    /**
     * Creates an instance of a {@code ServingController} class.
     *
     * @param service the value for the {@code service} component
     */
    public ServingController(final ServingService service) {
        this.service = service;
    }

    /**
     * Creates a new serving.
     *
     * @param food the id of the food
     * @param dto the data for the serving
     * @return the data of the serving
     */
    @PostMapping
    public ServingDto post(@PathVariable final Long food,
                           @RequestBody @Valid final ServingPostDto dto) {
        return service.post(food, dto);
    }

    /**
     * Returns the data of all servings.
     *
     * @param food the id of the food
     * @return a list of all servings' data
     */
    @GetMapping
    public List<ServingDto> get(@PathVariable final Long food) {
        return service.get(food);
    }

    /**
     * Returns the data of the serving with the given id.
     *
     * @param food the id of the food
     * @param id the id of the serving
     * @return the data of the serving
     */
    @GetMapping("/{id}")
    public ServingDto get(@PathVariable final Long food,
                          @PathVariable final Long id) {
        return service.get(food, id);
    }

    /**
     * Partially updates the data of the serving with the given id.
     *
     * @param id the id of the serving
     * @param dto the data for the serving
     * @return the data of the serving
     */
    @PatchMapping("/{id}")
    public ServingDto patch(@PathVariable final Long id,
                            @RequestBody @Valid final ServingPatchDto dto) {
        return service.patch(id, dto);
    }

    /**
     * Updates the data of the serving with the given id.
     *
     * @param id the id of the serving
     * @param dto the data for the serving
     * @return the data of the serving
     */
    @PutMapping("/{id}")
    public ServingDto put(@PathVariable final Long id,
                          @RequestBody @Valid final ServingPutDto dto) {
        return service.put(id, dto);
    }

    /**
     * Deletes the data of the serving with the given id.
     *
     * @param id the id of the serving
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable final Long id) {
        service.delete(id);
    }
}
