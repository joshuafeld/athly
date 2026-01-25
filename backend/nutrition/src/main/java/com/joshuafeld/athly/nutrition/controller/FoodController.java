package com.joshuafeld.athly.nutrition.controller;

import com.joshuafeld.athly.nutrition.dto.FoodDto;
import com.joshuafeld.athly.nutrition.dto.FoodPatchDto;
import com.joshuafeld.athly.nutrition.dto.FoodPostDto;
import com.joshuafeld.athly.nutrition.dto.FoodPutDto;
import com.joshuafeld.athly.nutrition.service.FoodService;
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
 * A food controller.
 */
@RestController
@RequestMapping("/foods")
public final class FoodController {

    private final FoodService service;

    /**
     * Creates an instance of an {@code FoodController} class.
     *
     * @param service the value for the {@code service} component
     */
    public FoodController(final FoodService service) {
        this.service = service;
    }

    /**
     * Creates a new food.
     *
     * @param dto the data for the food
     * @return the data of the food
     */
    @PostMapping
    public FoodDto post(@RequestBody @Valid final FoodPostDto dto) {
        return service.post(dto);
    }

    /**
     * Returns the data of all foods.
     *
     * @return a list of all foods' data
     */
    @GetMapping
    public List<FoodDto> get() {
        return service.get();
    }

    /**
     * Returns the data of the food with the given id.
     *
     * @param id the id of the food
     * @return the data of the food
     */
    @GetMapping("/{id}")
    public FoodDto get(@PathVariable final Long id) {
        return service.get(id);
    }

    /**
     * Partially updates the data of the food with the given id.
     *
     * @param id the id of the food
     * @param dto the data for the food
     * @return the data of the food
     */
    @PatchMapping("/{id}")
    public FoodDto patch(@PathVariable final Long id,
                         @RequestBody @Valid final FoodPatchDto dto) {
        return service.patch(id, dto);
    }

    /**
     * Updates the data of the food with the given id.
     *
     * @param id the id of the food
     * @param dto the data for the food
     * @return the data of the food
     */
    @PutMapping("/{id}")
    public FoodDto put(@PathVariable final Long id,
                       @RequestBody @Valid final FoodPutDto dto) {
        return service.put(id, dto);
    }

    /**
     * Deletes the data of the food with the given id.
     *
     * @param id the id of the food
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable final Long id) {
        service.delete(id);
    }
}

