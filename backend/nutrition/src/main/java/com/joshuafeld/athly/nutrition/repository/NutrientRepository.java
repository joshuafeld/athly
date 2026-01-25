package com.joshuafeld.athly.nutrition.repository;

import com.joshuafeld.athly.nutrition.exception.NutrientNotFoundException;
import com.joshuafeld.athly.nutrition.model.Nutrient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Objects;

/**
 * A nutrient repository.
 */
public interface NutrientRepository extends JpaRepository<Nutrient, Long> {

    /**
     * Retrieves all nutrients by their food id.
     *
     * @param food the id of the food
     * @return a list of all nutrients
     */
    default List<Nutrient> findAll(final Long food) {
        return findAll().stream().filter(nutrient ->
                Objects.equals(nutrient.food().id(), food)).toList();
    }

    /**
     * Retrieves a nutrient by its id.
     *
     * @param id the id of the nutrient
     * @return the nutrient
     * @throws IllegalArgumentException if {@code id} is {@code null}
     * @throws NutrientNotFoundException if the nutrient does not exist
     */
    default Nutrient requireById(final Long id) {
        return findById(id).orElseThrow(() -> new NutrientNotFoundException(id));
    }
}