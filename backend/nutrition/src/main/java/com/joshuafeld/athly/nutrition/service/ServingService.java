package com.joshuafeld.athly.nutrition.service;

import com.joshuafeld.athly.nutrition.dto.ServingDto;
import com.joshuafeld.athly.nutrition.dto.ServingPatchDto;
import com.joshuafeld.athly.nutrition.dto.ServingPostDto;
import com.joshuafeld.athly.nutrition.dto.ServingPutDto;
import com.joshuafeld.athly.nutrition.model.Serving;
import com.joshuafeld.athly.nutrition.repository.FoodRepository;
import com.joshuafeld.athly.nutrition.repository.ServingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * A serving service.
 */
@Service
public class ServingService {

    private final ServingRepository repository;
    private final FoodRepository foodRepository;

    /**
     * Creates an instance of an {@code ServingService} class.
     *
     * @param repository the value for the {@code repository} component
     * @param foodRepository the value for the {@code foodRepository} component
     */
    public ServingService(final ServingRepository repository,
                          final FoodRepository foodRepository) {
        this.repository = repository;
        this.foodRepository = foodRepository;
    }

    /**
     * Creates a new serving.
     *
     * @param food the id of the food
     * @param dto the data for the serving
     * @return the data of the serving
     */
    @Transactional
    public ServingDto post(final Long food, final ServingPostDto dto) {
        return toDto(repository.save(new Serving(
                foodRepository.requireById(food),
                dto.type(), dto.value(), dto.unit()
        )));
    }

    /**
     * Returns the data of all servings.
     *
     * @param food the id of the food
     * @return a list of all servings' data
     */
    @Transactional(readOnly = true)
    public List<ServingDto> get(final Long food) {
        return repository.findAll(food).stream().map(this::toDto).toList();
    }

    /**
     * Returns the data of the serving with the given id.
     *
     * @param food the id of the food
     * @param id the id of the serving
     * @return the data of the serving
     */
    @Transactional(readOnly = true)
    public ServingDto get(final Long food, final Long id) {
        return toDto(repository.requireById(id));
    }

    /**
     * Partially updates the data of the serving with the given id.
     *
     * @param id the id of the serving
     * @param dto the data for the serving
     * @return the data of the serving
     */
    @Transactional
    public ServingDto patch(final Long id, final ServingPatchDto dto) {
        Serving serving = repository.requireById(id);
        Optional.ofNullable(dto.type()).ifPresent(serving::type);
        Optional.ofNullable(dto.value()).ifPresent(serving::value);
        Optional.ofNullable(dto.unit()).ifPresent(serving::unit);
        return toDto(repository.save(serving));
    }

    /**
     * Updates the data of the serving with the given id.
     *
     * @param id the id of the serving
     * @param dto the data for the serving
     * @return the data of the serving
     */
    @Transactional
    public ServingDto put(final Long id, final ServingPutDto dto) {
        Serving serving = repository.requireById(id);
        serving.type(dto.type());
        serving.value(dto.value());
        serving.unit(dto.unit());
        return toDto(repository.save(serving));
    }

    /**
     * Deletes the data of the serving with the given id.
     *
     * @param id the id of the serving
     */
    @Transactional
    public void delete(final Long id) {
        repository.deleteById(id);
    }

    private ServingDto toDto(final Serving serving) {
        return new ServingDto(
                serving.id(),
                serving.food().id(),
                serving.type(),
                serving.value(),
                serving.unit()
        );
    }
}
