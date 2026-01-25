package com.joshuafeld.athly.nutrition.service;

import com.joshuafeld.athly.nutrition.dto.NutrientDto;
import com.joshuafeld.athly.nutrition.dto.NutrientPatchDto;
import com.joshuafeld.athly.nutrition.dto.NutrientPostDto;
import com.joshuafeld.athly.nutrition.dto.NutrientPutDto;
import com.joshuafeld.athly.nutrition.model.Nutrient;
import com.joshuafeld.athly.nutrition.repository.FoodRepository;
import com.joshuafeld.athly.nutrition.repository.NutrientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * A nutrient service.
 */
@Service
public class NutrientService {

    private final NutrientRepository repository;
    private final FoodRepository foodRepository;

    /**
     * Creates an instance of an {@code NutrientService} class.
     *
     * @param repository the value for the {@code repository} component
     * @param foodRepository the value for the {@code foodRepository} component
     */
    public NutrientService(final NutrientRepository repository,
                           final FoodRepository foodRepository) {
        this.repository = repository;
        this.foodRepository = foodRepository;
    }

    /**
     * Creates a new nutrient.
     *
     * @param food the id of the food
     * @param dto the data for the nutrient
     * @return the data of the nutrient
     */
    @Transactional
    public NutrientDto post(final Long food, final NutrientPostDto dto) {
        return toDto(repository.save(new Nutrient(
                foodRepository.requireById(food), dto.type(), dto.value()
        )));
    }

    /**
     * Returns the data of all nutrients.
     *
     * @param food the id of the food
     * @return a list of all nutrients' data
     */
    @Transactional(readOnly = true)
    public List<NutrientDto> get(final Long food) {
        return repository.findAll(food).stream().map(this::toDto).toList();
    }

    /**
     * Returns the data of the nutrient with the given id.
     *
     * @param food the id of the food
     * @param id the id of the nutrient
     * @return the data of the nutrient
     */
    @Transactional(readOnly = true)
    public NutrientDto get(final Long food, final Long id) {
        return toDto(repository.requireById(id));
    }

    /**
     * Partially updates the data of the nutrient with the given id.
     *
     * @param id the id of the nutrient
     * @param dto the data for the nutrient
     * @return the data of the nutrient
     */
    @Transactional
    public NutrientDto patch(final Long id, final NutrientPatchDto dto) {
        Nutrient nutrient = repository.requireById(id);
        Optional.ofNullable(dto.type()).ifPresent(nutrient::type);
        Optional.ofNullable(dto.value()).ifPresent(nutrient::value);
        return toDto(repository.save(nutrient));
    }

    /**
     * Updates the data of the nutrient with the given id.
     *
     * @param id the id of the nutrient
     * @param dto the data for the nutrient
     * @return the data of the nutrient
     */
    @Transactional
    public NutrientDto put(final Long id, final NutrientPutDto dto) {
        Nutrient nutrient = repository.requireById(id);
        nutrient.type(dto.type());
        nutrient.value(dto.value());
        return toDto(repository.save(nutrient));
    }

    /**
     * Deletes the data of the nutrient with the given id.
     *
     * @param id the id of the nutrient
     */
    @Transactional
    public void delete(final Long id) {
        repository.deleteById(id);
    }

    private NutrientDto toDto(final Nutrient nutrient) {
        return new NutrientDto(
                nutrient.id(),
                nutrient.food().id(),
                nutrient.type(),
                nutrient.value()
        );
    }
}
