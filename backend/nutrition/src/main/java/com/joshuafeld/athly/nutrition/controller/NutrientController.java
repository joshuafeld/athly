package com.joshuafeld.athly.nutrition.controller;

import com.joshuafeld.athly.nutrition.dto.NutrientDto;
import com.joshuafeld.athly.nutrition.dto.NutrientPatchDto;
import com.joshuafeld.athly.nutrition.dto.NutrientPostDto;
import com.joshuafeld.athly.nutrition.dto.NutrientPutDto;
import com.joshuafeld.athly.nutrition.service.NutrientService;
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
 * A nutrient controller.
 */
@RestController
@RequestMapping("/foods/{food}/nutrients")
public final class NutrientController {

    private final NutrientService service;

    /**
     * Creates an instance of a {@code NutrientController} class.
     *
     * @param service the value for the {@code service} component
     */
    public NutrientController(final NutrientService service) {
        this.service = service;
    }

    /**
     * Creates a new nutrient.
     *
     * @param food the id of the food
     * @param dto the data for the nutrient
     * @return the data of the nutrient
     */
    @PostMapping
    public NutrientDto post(@PathVariable final Long food,
                           @RequestBody @Valid final NutrientPostDto dto) {
        return service.post(food, dto);
    }

    /**
     * Returns the data of all nutrients.
     *
     * @param food the id of the food
     * @return a list of all nutrients' data
     */
    @GetMapping
    public List<NutrientDto> get(@PathVariable final Long food) {
        return service.get(food);
    }

    /**
     * Returns the data of the nutrient with the given id.
     *
     * @param food the id of the food
     * @param id the id of the nutrient
     * @return the data of the nutrient
     */
    @GetMapping("/{id}")
    public NutrientDto get(@PathVariable final Long food,
                          @PathVariable final Long id) {
        return service.get(food, id);
    }

    /**
     * Partially updates the data of the nutrient with the given id.
     *
     * @param id the id of the nutrient
     * @param dto the data for the nutrient
     * @return the data of the nutrient
     */
    @PatchMapping("/{id}")
    public NutrientDto patch(@PathVariable final Long id,
                             @RequestBody @Valid final NutrientPatchDto dto) {
        return service.patch(id, dto);
    }

    /**
     * Updates the data of the nutrient with the given id.
     *
     * @param id the id of the nutrient
     * @param dto the data for the nutrient
     * @return the data of the nutrient
     */
    @PutMapping("/{id}")
    public NutrientDto put(@PathVariable final Long id,
                           @RequestBody @Valid final NutrientPutDto dto) {
        return service.put(id, dto);
    }

    /**
     * Deletes the data of the nutrient with the given id.
     *
     * @param id the id of the nutrient
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable final Long id) {
        service.delete(id);
    }
}
