package com.joshuafeld.athly.nutrition.mapper;

import com.joshuafeld.athly.common.mapper.Mapper;
import com.joshuafeld.athly.nutrition.dto.FoodDto;
import com.joshuafeld.athly.nutrition.model.Food;
import com.joshuafeld.athly.nutrition.model.Nutrient;
import com.joshuafeld.athly.nutrition.model.Serving;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * A data transfer object mapper for foods.
 */
@Component
public class FoodMapper implements Mapper<Food, FoodDto> {

    /**
     * Converts a food entity to a data transfer object.
     *
     * @param food the food entity
     * @return the data transfer object
     */
    @Override
    public FoodDto toDto(final Food food) {
        return new FoodDto(
                food.id(),
                food.name(),
                food.manufacturer(),
                food.nutrients().stream().map(Nutrient::id).collect(Collectors
                        .toSet()),
                food.servings().stream().map(Serving::id).collect(Collectors
                        .toSet()),
                food.creator()
        );
    }
}
