package com.joshuafeld.athly.nutrition.service;

import com.joshuafeld.athly.nutrition.dto.FoodDto;
import com.joshuafeld.athly.nutrition.dto.FoodPatchDto;
import com.joshuafeld.athly.nutrition.dto.FoodPostDto;
import com.joshuafeld.athly.nutrition.dto.FoodPutDto;
import com.joshuafeld.athly.nutrition.model.Food;
import com.joshuafeld.athly.nutrition.model.Nutrient;
import com.joshuafeld.athly.nutrition.model.Serving;
import com.joshuafeld.athly.nutrition.repository.FoodRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A food service.
 */
@Service
public class FoodService {

    private final FoodRepository repository;

    /**
     * Creates an instance of a {@code FoodService} class.
     *
     * @param repository the value for the {@code repository} component
     */
    public FoodService(final FoodRepository repository) {
        this.repository = repository;
    }

    /**
     * Creates a new food.
     *
     * @param dto the data for the food
     * @return the data of the food
     */
    @Transactional
    public FoodDto post(final FoodPostDto dto) {
        return toDto(repository.save(new Food(
                dto.name(),
                dto.manufacturer(),
                new ArrayList<>(),
                new ArrayList<>(),
                dto.creator()
        )));
    }

    /**
     * Returns the data of all foods.
     *
     * @return a list of all foods' data
     */
    @Transactional(readOnly = true)
    public List<FoodDto> get() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    /**
     * Returns the data of the food with the given id.
     *
     * @param id the id of the food
     * @return the data of the food
     */
    @Transactional(readOnly = true)
    public FoodDto get(final Long id) {
        return toDto(repository.requireById(id));
    }

    /**
     * Partially updates the data of the food with the given id.
     *
     * @param id the id of the food
     * @param dto the data for the food
     * @return the data of the food
     */
    @Transactional
    public FoodDto patch(final Long id, final FoodPatchDto dto) {
        Food food = repository.requireById(id);
        Optional.ofNullable(dto.name()).ifPresent(food::name);
        Optional.ofNullable(dto.manufacturer()).ifPresent(food::manufacturer);
        Optional.ofNullable(dto.creator()).ifPresent(food::creator);
        return toDto(repository.save(food));
    }

    /**
     * Updates the data of the food with the given id.
     *
     * @param id the id of the food
     * @param dto the data for the food
     * @return the data of the food
     */
    @Transactional
    public FoodDto put(final Long id, final FoodPutDto dto) {
        Food food = repository.requireById(id);
        food.name(dto.name());
        food.manufacturer(dto.manufacturer());
        food.creator(dto.creator());
        return toDto(repository.save(food));
    }

    /**
     * Deletes the data of the food with the given id.
     *
     * @param id the id of the food
     */
    @Transactional
    public void delete(final Long id) {
        repository.deleteById(id);
    }

    private FoodDto toDto(final Food food) {
        return new FoodDto(
                food.id(),
                food.name(),
                food.manufacturer(),
                food.nutrients().stream().map(Nutrient::id).toList(),
                food.servings().stream().map(Serving::id).toList(),
                food.creator()
        );
    }
}
