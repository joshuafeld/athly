package com.joshuafeld.athly.nutrition.repository;

import com.joshuafeld.athly.nutrition.exception.FoodNotFoundException;
import com.joshuafeld.athly.nutrition.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A food repository.
 */
@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

    /**
     * Retrieves a food by its id.
     *
     * @param id the id of the food
     * @return the food
     * @throws IllegalArgumentException if {@code id} is {@code null}
     * @throws FoodNotFoundException if the food does not exist
     */
    default Food requireById(final Long id) {
        return findById(id).orElseThrow(() -> new FoodNotFoundException(id));
    }
}

